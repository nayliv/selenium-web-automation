@cart
Feature: Shopping Cart

  As an authenticated user
  I want to manage products in my shopping cart
  So that I can review my selected items before checkout

  Background:
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products page should be displayed

  @smoke @positive
  Scenario: Display a product added to the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    When the user opens the cart
    Then the cart page should be displayed
    And the product "Sauce Labs Backpack" should be displayed in the cart

  @positive @validation
  Scenario: Display product information in the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    When the user opens the cart
    Then the product "Sauce Labs Backpack" should display its name, description, price, and quantity

  @positive @multiple-products
  Scenario: Display multiple products in the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    And the product "Sauce Labs Bike Light" has been added to the cart
    When the user opens the cart
    Then the cart should contain 2 products
    And the product "Sauce Labs Backpack" should be displayed in the cart
    And the product "Sauce Labs Bike Light" should be displayed in the cart

  @positive @remove
  Scenario: Remove a product from the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    And the user opens the cart
    When the user removes the product "Sauce Labs Backpack" from the cart
    Then the product "Sauce Labs Backpack" should not be displayed in the cart

  @positive @navigation
  Scenario: Continue shopping from the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    And the user opens the cart
    When the user chooses to continue shopping
    Then the products page should be displayed

  @smoke @positive @checkout
  Scenario: Proceed to checkout
    Given the product "Sauce Labs Backpack" has been added to the cart
    And the user opens the cart
    When the user proceeds to checkout
    Then the checkout information page should be displayed