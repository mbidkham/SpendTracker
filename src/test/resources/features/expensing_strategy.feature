Feature: Test Expensing Strategy Feature
    Background:
        When I logged in.

    Scenario:
        Given I want to add my travel expenses.
        When I had spent 300_000 up to now in my travel.
        Then I got notified to spend too many in my travel.
