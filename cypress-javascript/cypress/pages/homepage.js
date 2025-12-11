/// <reference types="cypress" />

class HomePage {
  constructor() {
    this.chapter3Card = () => cy.findByText('Chapter 3. WebDriver Fundamentals');
    this.chapter4Card = () => cy.findByText('Chapter 4. Browser-Agnostic Features');
    this.chapter5Card = () => cy.findByText('Chapter 5. Browser-Specific Manipulation');
    this.chapter7Card = () => cy.findByText('Chapter 7. The Page Object Model (POM)');
    this.chapter8Card = () => cy.findByText('Chapter 8. Testing Framework Specifics');
    this.chapter9Card = () => cy.findByText('Chapter 9. Third-Party Integrations');
  }
}

export default new HomePage();
