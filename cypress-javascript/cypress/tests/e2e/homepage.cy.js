/// <reference types="cypress" />

import HomePage from '../../pages/homepage.js';

describe('Homepage Tests', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should load the homepage successfully', () => {
    cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
  });

  it('should display all chapter cards', () => {
    HomePage.chapter3Card().should('be.visible');
    HomePage.chapter4Card().should('be.visible');
    HomePage.chapter5Card().should('be.visible');
    HomePage.chapter7Card().should('be.visible');
    HomePage.chapter8Card().should('be.visible');
    HomePage.chapter9Card().should('be.visible');
  });
});
