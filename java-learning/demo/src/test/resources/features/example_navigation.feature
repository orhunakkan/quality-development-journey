Feature: Example navigation

  Scenario: Verify example page title
    Given I open the example page
    Then the page title should be "Example Domain"

  Scenario: Verify example page heading
    When I navigate to example domain
    Then the heading text should be "Example Domain"

  Scenario: Verify page contains expected title
    Given I open the example page
    Then the page title contains "Example"
