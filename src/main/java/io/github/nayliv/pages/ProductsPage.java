package io.github.nayliv.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage {

    private final By inventoryList =
            By.className("inventory_list");

    private final By inventoryItems =
            By.className("inventory_item");

    private final By productName =
            By.className("inventory_item_name");

    private final By productDescription =
            By.className("inventory_item_desc");

    private final By productPrice =
            By.className("inventory_item_price");

    private final By productActionButton =
            By.cssSelector("button.btn_inventory");

    private final By sortDropdown =
            By.className("product_sort_container");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By cartLink =
            By.className("shopping_cart_link");

    public boolean isDisplayed() {
        return isDisplayed(inventoryList);
    }

    public boolean isProductListDisplayed() {
        return isDisplayed(inventoryList)
                && !driver().findElements(inventoryItems).isEmpty();
    }

    public int getProductCount() {
        return driver()
                .findElements(inventoryItems)
                .size();
    }

    public boolean allProductsHaveRequiredInformation() {
        List<WebElement> products =
                driver().findElements(inventoryItems);

        if (products.isEmpty()) {
            return false;
        }

        return products.stream()
                .allMatch(this::hasRequiredInformation);
    }

    public void addProductToCart(String productName) {
        WebElement product = findProduct(productName);

        product.findElement(productActionButton)
                .click();
    }

    public void removeProduct(String productName) {
        WebElement product = findProduct(productName);

        product.findElement(productActionButton)
                .click();
    }

    public boolean isProductAvailableToRemove(String productName) {
        String buttonText = getProductActionButtonText(productName);

        return buttonText.equalsIgnoreCase("Remove");
    }

    public boolean isProductAvailableToAdd(String productName) {
        String buttonText = getProductActionButtonText(productName);

        return buttonText.equalsIgnoreCase("Add to cart");
    }

    public boolean isCartBadgeDisplayed() {
        List<WebElement> badges =
                driver().findElements(cartBadge);

        return !badges.isEmpty()
                && badges.getFirst().isDisplayed();
    }

    public String getCartBadgeText() {
        if (!isCartBadgeDisplayed()) {
            return "";
        }

        return getText(cartBadge);
    }

    public void openProduct(String productName) {
        WebElement product = findProduct(productName);

        product.findElement(this.productName)
                .click();
    }

    public void openCart() {
        click(cartLink);
    }

    public void sortProductsBy(String sortOption) {
        Select select =
                new Select(find(sortDropdown));

        select.selectByVisibleText(sortOption);
    }

    public List<String> getProductNames() {
        return driver()
                .findElements(productName)
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<Double> getProductPrices() {
        return driver()
                .findElements(productPrice)
                .stream()
                .map(WebElement::getText)
                .map(price -> price.replace("$", ""))
                .map(Double::parseDouble)
                .toList();
    }

    private WebElement findProduct(String expectedProductName) {
        return driver()
                .findElements(inventoryItems)
                .stream()
                .filter(product ->
                        product.findElement(productName)
                                .getText()
                                .equals(expectedProductName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Product not found: "
                                        + expectedProductName
                        )
                );
    }

    private String getProductActionButtonText(String productName) {
        WebElement product = findProduct(productName);

        return product
                .findElement(productActionButton)
                .getText();
    }

    private boolean hasRequiredInformation(WebElement product) {
        String name =
                product.findElement(productName)
                        .getText();

        String description =
                product.findElement(productDescription)
                        .getText();

        String price =
                product.findElement(productPrice)
                        .getText();

        boolean actionButtonDisplayed =
                product.findElement(productActionButton)
                        .isDisplayed();

        return !name.isBlank()
                && !description.isBlank()
                && !price.isBlank()
                && actionButtonDisplayed;
    }
}