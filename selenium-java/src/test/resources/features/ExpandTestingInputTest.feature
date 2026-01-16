Feature: ExpandTesting Input Test
  As a test automation engineer
  I want to validate that the ExpandTesting inputs page loads correctly
  And the buttons are displayed

  Scenario: Verify inputs page loads with correct elements
    Given I navigate to the ExpandTesting inputs page
    Then the page title should contain "inputs"
    And the Display Inputs button should be displayed
    And the Clear button should be displayed
