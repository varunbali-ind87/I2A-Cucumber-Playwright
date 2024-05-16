@Test
Feature: Sample scenarios for Playwright

  Background: User is on the I2A website
    Given the user is on "https://action.deloitte.com/"

  Scenario: Verify I2A search feature
    When the user enters "digital" in the searchbox & submits
    Then he should see some results

  Scenario: Verify About us page
    When the user clicks About Us
    Then he should be taken to the about us page

  Scenario: Verify the sign in page
    When the user clicks Sign In link
    Then he should be taken to the sign in page