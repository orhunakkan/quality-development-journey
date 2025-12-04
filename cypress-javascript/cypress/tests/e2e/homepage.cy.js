import HomePage from '../../pages/homepage.js';

describe('Homepage Tests', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should load the homepage successfully', () => {
    cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
  });

  it('should display all chapter cards', () => {
    cy.contains(HomePage.chapter3Card).should('be.visible');
    cy.contains(HomePage.chapter4Card).should('be.visible');
    cy.contains(HomePage.chapter5Card).should('be.visible');
    cy.contains(HomePage.chapter7Card).should('be.visible');
    cy.contains(HomePage.chapter8Card).should('be.visible');
    cy.contains(HomePage.chapter9Card).should('be.visible');
  });
});
