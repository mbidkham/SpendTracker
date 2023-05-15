Feature: Test Categorizing Strategy Feature
    Background:
        When I logged in and got token.

    Scenario: Call /category/add service by valid input data
        Given I want to add new category for myself
        When I entered valid category name and limit value
        Then Show the message "Added Successfully."

    Scenario: Call /category/search service to get my categories
        Given I want to see my categories having names containing 'cof'
        When There is 1 category with name 'coffee'
        Then I get my categories

