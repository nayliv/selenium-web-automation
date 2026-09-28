@products
Feature: Products

  As an authenticated user
  I want to view and interact with the available products
  So that I can choose products to purchase

  Background:
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products page should be displayed

  @smoke @positive
  Scenario: Display available products
    Then the product list should be displayed
    And each product should display a name, description, price, and action button

  @positive @cart
  Scenario: Add a product to the cart
    When the user adds the product "Sauce Labs Backpack" to the cart
    Then the cart badge should display "1"
    And the product "Sauce Labs Backpack" should be available to remove

  @positive @cart
  Scenario: Remove a product from the cart
    Given the product "Sauce Labs Backpack" has been added to the cart
    When the user removes the product "Sauce Labs Backpack"
    Then the cart badge should not be displayed
    And the product "Sauce Labs Backpack" should be available to add

  @positive @cart
  Scenario: Add multiple products to the cart
    When the user adds the product "Sauce Labs Backpack" to the cart
    And the user adds the product "Sauce Labs Bike Light" to the cart
    Then the cart badge should display "2"

  @positive @navigation
  Scenario: Open product details
    When the user opens the product "Sauce Labs Backpack"
    Then the product details page should be displayed
    And the product name should be "Sauce Labs Backpack"

  @positive @sorting
  Scenario Outline: Sort products
    When the user sorts the products by "<sortOption>"
    Then the products should be ordered by "<expectedOrder>"

    Examples:
      | sortOption           | expectedOrder    |
      | Name (A to Z)        | name ascending   |
      | Name (Z to A)        | name descending  |
      | Price (low to high)  | price ascending  |
      | Price (high to low)  | price descending |