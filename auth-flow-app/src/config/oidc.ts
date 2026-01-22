import { Issuer, Client } from 'openid-client';
import { env } from './env.js';

let oidcClient: Client | null = null;

export async function getOIDCClient(): Promise<Client> {
    if (oidcClient) {
        return oidcClient;
    }

    const issuer = await Issuer.discover(env.KEYCLOAK_ISSUER_URL);

    oidcClient = new issuer.Client({
        client_id: env.OIDC_CLIENT_ID,
        client_secret: env.OIDC_CLIENT_SECRET,
        redirect_uris: [env.OIDC_REDIRECT_URI],
        response_types: ['code'],
    });

    return oidcClient;
}
