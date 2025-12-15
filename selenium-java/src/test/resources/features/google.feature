Feature: Google Search Tests

  Scenario: Verify Google page title
    Given I navigate to Google homepage
    Then the page title should contain "Google"

  Scenario: Verify Google search box is functional
    Given I navigate to Google homepage
    Then the search box should be present
    And the search box should be displayed
    And the search box should be enabled
