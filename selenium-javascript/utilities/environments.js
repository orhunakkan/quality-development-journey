import dotenv from 'dotenv';
dotenv.config();

export function getBaseUrl() {
    const env = process.env.ENV || 'prod';
    switch (env) {
        case 'prod':
            return process.env.PROD_URL;
        case 'uat':
            return process.env.UAT_URL;
        case 'staging':
            return process.env.STAGING_URL;
        default:
            throw new Error(`Unknown ENV: ${env}`);
    }
}