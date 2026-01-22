import dotenv from 'dotenv';

dotenv.config();

interface EnvVars {
    PORT: number;
    BASE_URL: string;
    SESSION_SECRET: string;
    SESSION_COOKIE_SECURE: boolean;
    SESSION_COOKIE_SAMESITE: 'lax' | 'strict' | 'none';
    DATABASE_URL: string;
    KEYCLOAK_ISSUER_URL: string;
    OIDC_CLIENT_ID: string;
    OIDC_CLIENT_SECRET: string;
    OIDC_REDIRECT_URI: string;
}

function getEnv(): EnvVars {
    const required = [
        'PORT',
        'BASE_URL',
        'SESSION_SECRET',
        'DATABASE_URL',
        'KEYCLOAK_ISSUER_URL',
        'OIDC_CLIENT_ID',
        'OIDC_CLIENT_SECRET',
        'OIDC_REDIRECT_URI',
    ];

    const missing = required.filter((key) => !process.env[key]);
    if (missing.length > 0) {
        throw new Error(`Missing required environment variables: ${missing.join(', ')}`);
    }

    return {
        PORT: parseInt(process.env.PORT || '3000', 10),
        BASE_URL: process.env.BASE_URL!,
        SESSION_SECRET: process.env.SESSION_SECRET!,
        SESSION_COOKIE_SECURE: process.env.SESSION_COOKIE_SECURE === 'true',
        SESSION_COOKIE_SAMESITE:
            (process.env.SESSION_COOKIE_SAMESITE as 'lax' | 'strict' | 'none') || 'lax',
        DATABASE_URL: process.env.DATABASE_URL!,
        KEYCLOAK_ISSUER_URL: process.env.KEYCLOAK_ISSUER_URL!,
        OIDC_CLIENT_ID: process.env.OIDC_CLIENT_ID!,
        OIDC_CLIENT_SECRET: process.env.OIDC_CLIENT_SECRET!,
        OIDC_REDIRECT_URI: process.env.OIDC_REDIRECT_URI!,
    };
}

export const env = getEnv();
