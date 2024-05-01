@tag
Feature: Error Validation
  Background:
    Given I landed on Ecommerce page
  @tag2
  Scenario Outline:
    Given Logged in with userName<userName> and password<password>
    Then "Incorrect email or password." message is displayed.
    Examples:
      | userName      | password   |
      | ssh@gmail.com | Hellomampu |
