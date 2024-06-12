@Test
Feature: Test cases for Topics

  Background: User is on the I2A website
    Given the user is on "https://action.deloitte.com/"

  Scenario: Verify that the user is able to filter contents
    When the user visits "Consumer" topic
    And filters "Trends" contents
    Then the filtered results should be displayed
