import express, { Request, Response } from 'express';
import session from 'express-session';
import pgSession from 'connect-pg-simple';
import { Pool } from 'pg';
import { env } from './config/env.js';
import authRouter from './routes/auth.js';
import { publicRouter, protectedRouter, apiRouter } from './routes/index.js';
import { logger } from './utils/logger.js';


const app = express();

// Parse JSON and URL-encoded bodies
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// Create database connection pool
const pool = new Pool({
    connectionString: env.DATABASE_URL,
});

pool.on('error', (err: Error) => {
    logger('Unexpected error on idle client', err);
});

// Test database connection
pool.query('SELECT NOW()', (err: Error | null, result: any) => {
    if (err) {
        logger('Failed to connect to database', err);
    } else {
        logger('Successfully connected to database');
    }
});

// Set up session store
const PostgresStore = pgSession(session);

const sessionStore = new PostgresStore({
    pool: pool,
    tableName: 'session',
    createTableIfMissing: true,
});

// Session middleware
app.use(
    session({
        store: sessionStore,
        secret: env.SESSION_SECRET,
        resave: false,
        saveUninitialized: false,
        cookie: {
            secure: env.SESSION_COOKIE_SECURE,
            httpOnly: true,
            sameSite: env.SESSION_COOKIE_SAMESITE,
            maxAge: 60 * 60 * 1000, // 1 hour
        },
    })
);

// Routes
app.use('/', publicRouter);
app.use('/auth', authRouter);
app.use('/', protectedRouter);
app.use('/api', apiRouter);

// 404 handler
app.use((req: Request, res: Response) => {
    res.status(404).send(`
    <!DOCTYPE html>
    <html>
    <head><title>404 - Not Found</title></head>
    <body>
      <h1>404 - Page Not Found</h1>
      <a href="/">Back to Home</a>
    </body>
    </html>
  `);
});

// Error handler
app.use((err: any, req: Request, res: Response) => {
    logger('Unhandled error', err);
    res.status(500).send(`
    <!DOCTYPE html>
    <html>
    <head><title>500 - Internal Server Error</title></head>
    <body>
      <h1>500 - Internal Server Error</h1>
      <p>Something went wrong. Please try again later.</p>
      <a href="/">Back to Home</a>
    </body>
    </html>
  `);
});

// Start server
const PORT = env.PORT;
app.listen(PORT, () => {
    logger(`Server running on ${env.BASE_URL}`);
    logger(`Keycloak issuer: ${env.KEYCLOAK_ISSUER_URL}`);
});

// Graceful shutdown
process.on('SIGTERM', () => {
    logger('SIGTERM received, shutting down gracefully');
    pool.end(() => {
        logger('Database pool closed');
        process.exit(0);
    });
});
