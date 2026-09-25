@login
Feature: Login

  As a user
  I want to authenticate in the application
  So that I can access the products page

  @smoke @positive
  Scenario: Login successfully with valid credentials
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products page should be displayed

  @negative
  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user logs in with username "invalid_user" and password "invalid_password"
    Then the login error message should be "Epic sadface: Username and password do not match any user in this service"

  @negative @validation
  Scenario: Login without username
    Given the user is on the login page
    When the user logs in with username "" and password "secret_sauce"
    Then the login error message should be "Epic sadface: Username is required"

  @negative @validation
  Scenario: Login without password
    Given the user is on the login page
    When the user logs in with username "standard_user" and password ""
    Then the login error message should be "Epic sadface: Password is required"

  @negative @validation
  Scenario: Login without username and password
    Given the user is on the login page
    When the user logs in with username "" and password ""
    Then the login error message should be "Epic sadface: Username is required"

  @negative
  Scenario: Login with locked user
    Given the user is on the login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then the login error message should be "Epic sadface: Sorry, this user has been locked out."