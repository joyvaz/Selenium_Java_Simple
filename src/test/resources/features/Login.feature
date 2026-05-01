@Login
Feature: Login

  Scenario Outline: Login <dataSet>

    Given I open the link "sauceUrl"
    When I set the credential of the user "<user>" on the login page
    And I click on The Login button on the login page
    Then I verify an error <errorMessage> <state> displayed on the login page

	Examples:
		| dataSet | user          | errorMessage                                           | state  |
		| success | standard_user |                                                        | is not |
		| Locked  | locked_user   |  "Epic sadface: Sorry, this user has been locked out." |  is    |