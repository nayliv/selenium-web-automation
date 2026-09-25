package io.github.nayliv.pages;

import io.github.nayliv.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    protected WebDriver driver() {
        WebDriver driver = DriverManager.getDriver();

        if (driver == null) {
            throw new IllegalStateException(
                    "WebDriver is not initialized. " +
                            "Make sure the test extends BaseTest."
            );
        }

        return driver;
    }

    protected WebElement find(By locator) {
        return driver().findElement(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isDisplayed(By locator) {
        return find(locator).isDisplayed();
    }
}