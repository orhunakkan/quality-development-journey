import { defineConfig } from 'cypress';
import { getBaseUrl } from './cypress/utilities/environments.js';

export default defineConfig({
  e2e: {
    baseUrl: getBaseUrl(),
    specPattern: 'cypress/tests/**/*.cy.{js,jsx,ts,tsx}',
    setupNodeEvents(on, config) {
      return config;
    },
  },
});
