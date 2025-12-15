Feature: Google Search Tests

  Background:
    Given I navigate to Google homepage

  Scenario: Search box is ready for input
    Then the search box should be present
    And the search box should be displayed
    And the search box should be enabled

  Scenario Outline: Page title contains keyword
    Then the page title should contain "<keyword>"

    Examples:
      | keyword |
      | Google  |
      | Goo     |
