Feature: Post Login Strategy Feature

  Scenario: Call /auth/login service by valid authentication data
    Given I want to login
    When I entered valid user and pass
    Then Perform a valid token
