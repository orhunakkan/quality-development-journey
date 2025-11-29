import dotenv from 'dotenv';

dotenv.config();

export const getBaseUrl = () => {
    const env = process.env.ENV || 'prod';
    const envMap = {
        prod: process.env.PROD_BASE_URL,
        uat: process.env.UAT_BASE_URL,
        staging: process.env.STAGING_BASE_URL,
    };
    return envMap[env] || envMap.prod;
};
