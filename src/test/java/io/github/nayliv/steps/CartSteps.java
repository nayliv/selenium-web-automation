package io.github.nayliv.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.nayliv.pages.CartPage;
import io.github.nayliv.pages.CheckoutInformationPage;
import io.github.nayliv.pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartSteps {

    private CartPage cartPage;
    private ProductsPage productsPage;
    private CheckoutInformationPage checkoutInformationPage;

    private CartPage cartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }

        return cartPage;
    }

    @When("the user opens the cart")
    public void theUserOpensTheCart() {
        ProductsPage currentProductsPage =
                new ProductsPage();

        currentProductsPage.openCart();

        cartPage = new CartPage();
    }

    @Then("the cart page should be displayed")
    public void theCartPageShouldBeDisplayed() {
        assertTrue(
                cartPage().isDisplayed(),
                "The cart page was not displayed."
        );
    }

    @Then("the product {string} should be displayed in the cart")
    public void theProductShouldBeDisplayedInTheCart(
            String productName) {

        assertTrue(
                cartPage().isProductDisplayed(productName),
                "The product was not displayed in the cart: "
                        + productName
        );
    }

    @Then("the product {string} should display its name, description, price, and quantity")
    public void theProductShouldDisplayItsRequiredInformation(
            String productName) {

        assertTrue(
                cartPage().hasRequiredInformation(productName),
                "The product does not contain all required information: "
                        + productName
        );
    }

    @Then("the cart should contain {int} products")
    public void theCartShouldContainProducts(
            int expectedProductCount) {

        assertEquals(
                expectedProductCount,
                cartPage().getProductCount(),
                "The number of products in the cart is incorrect."
        );
    }

    @When("the user removes the product {string} from the cart")
    public void theUserRemovesTheProductFromTheCart(
            String productName) {

        cartPage().removeProduct(productName);
    }

    @Then("the product {string} should not be displayed in the cart")
    public void theProductShouldNotBeDisplayedInTheCart(
            String productName) {

        assertFalse(
                cartPage().isProductDisplayed(productName),
                "The product should not be displayed in the cart: "
                        + productName
        );
    }

    @When("the user chooses to continue shopping")
    public void theUserChoosesToContinueShopping() {
        productsPage =
                cartPage().continueShopping();
    }

    @When("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        checkoutInformationPage =
                cartPage().proceedToCheckout();
    }

    @Then("the checkout information page should be displayed")
    public void theCheckoutInformationPageShouldBeDisplayed() {
        assertTrue(
                checkoutInformationPage.isDisplayed(),
                "The checkout information page was not displayed."
        );
    }
}