describe('Homepage Tests', () => {
    it('should load the homepage successfully', () => {
        cy.visit('/');
        cy.title().should('eq', 'Hands-On Selenium WebDriver with Java');
    });
});