describe('Homepage Tests', () => {
  beforeEach(() => {
    cy.visit('/');
  });

  it('should load the homepage successfully', () => {
    cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
  });
});
