@GUI
Feature: WebDriver2 site security

  Scenario: I can log in with a valid username and password
    Given I am on the login page
    When I use the username "edgewords" and password "edgewords123x" to login
    Then I am successfully logged in