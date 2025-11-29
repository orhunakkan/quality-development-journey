import * as dotenv from 'dotenv';
dotenv.config();

export function getBaseUrl(): string {
  const env = (process.env.ENV || 'prod').toLowerCase();
  switch (env) {
    case 'prod':
      return process.env.PROD_URL as string;
    case 'uat':
      return process.env.UAT_URL as string;
    case 'staging':
      return process.env.STAGING_URL as string;
    default:
      throw new Error(`Unknown ENV: ${env}`);
  }
}
