package io.github.nayliv.pages;

import org.openqa.selenium.By;

public class ProductsPage extends BasePage {

    private final By pageTitle = By.cssSelector("[data-test='title']");

    public boolean isDisplayed() {
        return isDisplayed(pageTitle);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }
}
