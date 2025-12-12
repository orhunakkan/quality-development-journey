import { defineConfig } from 'cypress';
import 'cypress-mochawesome-reporter/plugin';
import dotenv from 'dotenv';

dotenv.config();

export default defineConfig({
  reporter: 'cypress-mochawesome-reporter',
  reporterOptions: {
    charts: true,
    reportPageTitle: 'Cypress Test Report',
    embeddedScreenshots: true,
    inlineAssets: true,
    saveAllAttempts: false,
  },
  e2e: {
    baseUrl: process.env.ENV,
    specPattern: 'cypress/tests/**/*.cy.{js,jsx,ts,tsx}',
    setupNodeEvents(on, config) {
      require('cypress-mochawesome-reporter/plugin')(on);
      config.env.API_URL = process.env.API_URL;
      return config;
    },
  },
});
