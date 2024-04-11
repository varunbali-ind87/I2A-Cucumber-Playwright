@Test
Feature: Sample scenarios for Playwright

  Scenario: Verify I2A search feature
    Given the user is on "https://action.deloitte.com/"
    When the user enters "digital" in the searchbox & submits
    Then he should see some results