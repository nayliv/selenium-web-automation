package io.github.nayliv.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.nayliv.pages.ProductDetailsPage;
import io.github.nayliv.pages.ProductsPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductsSteps {

    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;

    private ProductsPage productsPage() {
        if (productsPage == null) {
            productsPage = new ProductsPage();
        }

        return productsPage;
    }

    @Then("the product list should be displayed")
    public void theProductListShouldBeDisplayed() {
        assertTrue(
                productsPage().isProductListDisplayed(),
                "The product list was not displayed."
        );
    }

    @Then("each product should display a name, description, price, and action button")
    public void eachProductShouldDisplayRequiredInformation() {
        assertTrue(
                productsPage().allProductsHaveRequiredInformation(),
                "One or more products do not contain all required information."
        );
    }

    @When("the user adds the product {string} to the cart")
    public void theUserAddsTheProductToTheCart(String productName) {
        productsPage().addProductToCart(productName);
    }

    @Given("the product {string} has been added to the cart")
    public void theProductHasBeenAddedToTheCart(String productName) {
        productsPage().addProductToCart(productName);

        assertTrue(
                productsPage().isProductAvailableToRemove(productName),
                "The product was not added to the cart: " + productName
        );
    }

    @When("the user removes the product {string}")
    public void theUserRemovesTheProduct(String productName) {
        productsPage().removeProduct(productName);
    }

    @Then("the cart badge should display {string}")
    public void theCartBadgeShouldDisplay(String expectedQuantity) {
        assertTrue(
                productsPage().isCartBadgeDisplayed(),
                "The cart badge was not displayed."
        );

        assertEquals(
                expectedQuantity,
                productsPage().getCartBadgeText(),
                "The cart badge quantity is incorrect."
        );
    }

    @Then("the cart badge should not be displayed")
    public void theCartBadgeShouldNotBeDisplayed() {
        assertFalse(
                productsPage().isCartBadgeDisplayed(),
                "The cart badge should not be displayed."
        );
    }

    @Then("the product {string} should be available to remove")
    public void theProductShouldBeAvailableToRemove(String productName) {
        assertTrue(
                productsPage().isProductAvailableToRemove(productName),
                "The product should be available to remove: " + productName
        );
    }

    @Then("the product {string} should be available to add")
    public void theProductShouldBeAvailableToAdd(String productName) {
        assertTrue(
                productsPage().isProductAvailableToAdd(productName),
                "The product should be available to add: " + productName
        );
    }

    @When("the user opens the product {string}")
    public void theUserOpensTheProduct(String productName) {
        productsPage().openProduct(productName);

        productDetailsPage = new ProductDetailsPage();
    }

    @Then("the product details page should be displayed")
    public void theProductDetailsPageShouldBeDisplayed() {
        assertTrue(
                productDetailsPage.isDisplayed(),
                "The product details page was not displayed."
        );
    }

    @Then("the product name should be {string}")
    public void theProductNameShouldBe(String expectedProductName) {
        assertEquals(
                expectedProductName,
                productDetailsPage.getProductName(),
                "The product name is incorrect."
        );
    }

    @When("the user sorts the products by {string}")
    public void theUserSortsTheProductsBy(String sortOption) {
        productsPage().sortProductsBy(sortOption);
    }

    @Then("the products should be ordered by {string}")
    public void theProductsShouldBeOrderedBy(String expectedOrder) {

        switch (expectedOrder.toLowerCase()) {

            case "name ascending" ->
                    assertNamesAscending();

            case "name descending" ->
                    assertNamesDescending();

            case "price ascending" ->
                    assertPricesAscending();

            case "price descending" ->
                    assertPricesDescending();

            default ->
                    throw new IllegalArgumentException(
                            "Unsupported product order: " + expectedOrder
                    );
        }
    }

    private void assertNamesAscending() {
        List<String> actualNames =
                productsPage().getProductNames();

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        expectedNames.sort(
                String.CASE_INSENSITIVE_ORDER
        );

        assertEquals(
                expectedNames,
                actualNames,
                "Products are not sorted by name in ascending order."
        );
    }

    private void assertNamesDescending() {
        List<String> actualNames =
                productsPage().getProductNames();

        List<String> expectedNames =
                new ArrayList<>(actualNames);

        expectedNames.sort(
                String.CASE_INSENSITIVE_ORDER.reversed()
        );

        assertEquals(
                expectedNames,
                actualNames,
                "Products are not sorted by name in descending order."
        );
    }

    private void assertPricesAscending() {
        List<Double> actualPrices =
                productsPage().getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        expectedPrices.sort(
                Comparator.naturalOrder()
        );

        assertEquals(
                expectedPrices,
                actualPrices,
                "Products are not sorted by price in ascending order."
        );
    }

    private void assertPricesDescending() {
        List<Double> actualPrices =
                productsPage().getProductPrices();

        List<Double> expectedPrices =
                new ArrayList<>(actualPrices);

        expectedPrices.sort(
                Comparator.reverseOrder()
        );

        assertEquals(
                expectedPrices,
                actualPrices,
                "Products are not sorted by price in descending order."
        );
    }
}