package io.github.nayliv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartContainer =
            By.id("cart_contents_container");

    private final By cartItems =
            By.className("cart_item");

    private final By productName =
            By.className("inventory_item_name");

    private final By productDescription =
            By.className("inventory_item_desc");

    private final By productPrice =
            By.className("inventory_item_price");

    private final By productQuantity =
            By.className("cart_quantity");

    private final By productActionButton =
            By.tagName("button");

    private final By continueShoppingButton =
            By.id("continue-shopping");

    private final By checkoutButton =
            By.id("checkout");

    public boolean isDisplayed() {
        return isDisplayed(cartContainer);
    }

    public boolean isProductDisplayed(String expectedProductName) {
        return driver()
                .findElements(cartItems)
                .stream()
                .anyMatch(product ->
                        product.findElement(productName)
                                .getText()
                                .equals(expectedProductName)
                );
    }

    public int getProductCount() {
        return driver()
                .findElements(cartItems)
                .size();
    }

    public boolean hasRequiredInformation(String expectedProductName) {
        WebElement product = findProduct(expectedProductName);

        String name =
                product.findElement(productName)
                        .getText();

        String description =
                product.findElement(productDescription)
                        .getText();

        String price =
                product.findElement(productPrice)
                        .getText();

        String quantity =
                product.findElement(productQuantity)
                        .getText();

        return !name.isBlank()
                && !description.isBlank()
                && !price.isBlank()
                && !quantity.isBlank();
    }

    public void removeProduct(String expectedProductName) {
        WebElement product = findProduct(expectedProductName);

        product.findElement(productActionButton)
                .click();
    }

    public ProductsPage continueShopping() {
        click(continueShoppingButton);

        return new ProductsPage();
    }

    public CheckoutInformationPage proceedToCheckout() {
        click(checkoutButton);

        return new CheckoutInformationPage();
    }

    private WebElement findProduct(String expectedProductName) {
        List<WebElement> products =
                driver().findElements(cartItems);

        return products.stream()
                .filter(product ->
                        product.findElement(productName)
                                .getText()
                                .equals(expectedProductName)
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found in cart: "
                                        + expectedProductName
                        )
                );
    }
}