import { defineConfig, devices } from '@playwright/test';
import { getBaseUrl } from './utilities/environments';

export default defineConfig({
  testDir: './tests',
  testMatch: '**/*.spec.ts',
  timeout: 30000,
  fullyParallel: true,
  retries: process.env.CI ? 1 : 0,
  workers: process.env.CI ? 4 : undefined,
  reporter: [['list'], ['html'], ['json', { outputFile: 'test-results/report.json' }]],
  outputDir: 'test-results',
  use: {
    baseURL: getBaseUrl(),
    trace: 'on-first-retry',
    ignoreHTTPSErrors: true,
    screenshot: 'only-on-failure',
    video: 'retain-on-failure',
  },
  projects: [
    {
      name: 'Desktop Chrome',
      use: { ...devices['Desktop Chrome'] },
    },

    {
      name: 'Desktop Firefox',
      use: { ...devices['Desktop Firefox'] },
    },

    {
      name: 'Desktop Edge',
      use: { ...devices['Desktop Edge'] },
    },
  ],
});
