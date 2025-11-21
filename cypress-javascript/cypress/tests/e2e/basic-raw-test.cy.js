describe('Homepage URL validation', () => {
  it('should go to homepage and verify its URL', () => {
    cy.visit('https://bonigarcia.dev/selenium-webdriver-java/index.html');
    cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
  });
})