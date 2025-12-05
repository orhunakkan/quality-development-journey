import dotenv from 'dotenv';

dotenv.config();

export const getBaseUrl = () => process.env.ENV;
export const getApiBaseUrl = () => process.env.API_URL;
