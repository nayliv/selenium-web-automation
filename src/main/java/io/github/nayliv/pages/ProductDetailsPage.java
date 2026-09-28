package io.github.nayliv.pages;

import org.openqa.selenium.By;

public class ProductDetailsPage extends BasePage {

    private final By productDetails =
            By.className("inventory_details_container");

    private final By productName =
            By.className("inventory_details_name");

    public boolean isDisplayed() {
        return isDisplayed(productDetails);
    }

    public String getProductName() {
        return getText(productName);
    }
}