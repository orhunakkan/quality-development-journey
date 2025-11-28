describe('Homepage Tests', () => {
    it('should load the homepage successfully', () => {
        cy.visit('https://bonigarcia.dev/selenium-webdriver-java/index.html');
        cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
    });
});