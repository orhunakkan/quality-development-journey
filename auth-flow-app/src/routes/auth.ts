import { Router, Request, Response } from 'express';
import { getOIDCClient } from '../config/oidc.js';
import { generateRandomString, extractRolesFromToken } from '../auth/tokenValidation.js';
import { env } from '../config/env.js';
import { logger } from '../utils/logger.js';
import { SessionUser } from '../auth/session.js';


const authRouter = Router();

// GET /auth/login - initiates OIDC flow
authRouter.get('/login', async (req: Request, res: Response) => {
    try {
        const client = await getOIDCClient();
        const state = generateRandomString();
        const nonce = generateRandomString();

        // Store state and nonce in session for validation on callback
        req.session.state = state;
        req.session.nonce = nonce;

        const authorizationUrl = client.authorizationUrl({
            scope: 'openid profile email',
            state,
            nonce,
        });

        logger('User initiated login', { state: state.substring(0, 8) });
        res.redirect(authorizationUrl);
    } catch (error) {
        logger('Login error', error);
        res.status(500).send(`
      <!DOCTYPE html>
      <html>
      <head><title>Login Error</title></head>
      <body>
        <h1>Login Error</h1>
        <p>Failed to initiate login. Please try again.</p>
        <a href="/">Back to Home</a>
      </body>
      </html>
    `);
    }
});

// GET /auth/callback - handles OIDC callback
authRouter.get('/callback', async (req: Request, res: Response) => {
    try {
        const client = await getOIDCClient();
        const params = client.callbackParams(req);

        if (!params.code) {
            throw new Error('No authorization code received');
        }

        if (!req.session.state || params.state !== req.session.state) {
            throw new Error('Invalid state parameter - possible CSRF attack');
        }

        const storedNonce = req.session.nonce;

        // Exchange code for tokens
        const tokenSet = await client.callback(
            env.OIDC_REDIRECT_URI,
            params,
            { state: req.session.state, nonce: storedNonce }
        );

        // Validate token claims
        const idToken = tokenSet.claims();

        if (!idToken) {
            throw new Error('No ID token received');
        }

        // Validate issuer
        if (idToken.iss !== env.KEYCLOAK_ISSUER_URL) {
            throw new Error(`Invalid issuer: ${idToken.iss}`);
        }

        // Validate audience
        if (idToken.aud !== env.OIDC_CLIENT_ID) {
            throw new Error(`Invalid audience: ${idToken.aud}`);
        }

        // Validate nonce
        if (idToken.nonce !== req.session.nonce) {
            throw new Error('Invalid nonce - possible token reuse');
        }

        // Extract user info and roles
        const roles = extractRolesFromToken(idToken);

        const user: SessionUser = {
            userId: idToken.sub!,
            username: idToken.preferred_username || idToken.sub!,
            email: idToken.email,
            roles,
            loginTime: Date.now(),
            idToken: tokenSet.id_token,
        };

        // Regenerate session to prevent session fixation attacks
        req.session.regenerate((err) => {
            if (err) {
                throw err;
            }

            req.session.user = user;
            delete req.session.state;
            delete req.session.nonce;

            logger('User logged in successfully', {
                username: user.username,
                roles: user.roles,
            });

            res.redirect('/dashboard');
        });
    } catch (error) {
        logger('Callback error', error);
        res.status(400).send(`
      <!DOCTYPE html>
      <html>
      <head><title>Authentication Error</title></head>
      <body>
        <h1>Authentication Error</h1>
        <p>${error instanceof Error ? error.message : 'Unknown error'}</p>
        <a href="/">Back to Home</a>
      </body>
      </html>
    `);
    }
});

// POST or GET /auth/logout - clears session and logs out from Keycloak
authRouter.all('/logout', (req: Request, res: Response) => {
    logger('User logout initiated', { username: req.session.user?.username });

    // Destroy session
    req.session.destroy((err) => {
        if (err) {
            logger('Session destroy error', err);
            return res.status(500).send('Logout failed');
        }

        // Clear all session cookies
        res.clearCookie('connect.sid', { path: '/' });

        logger('Session destroyed');

        // Redirect home
        res.redirect('/');
    });
});

export default authRouter;
