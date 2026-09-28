package io.github.nayliv.pages;

import org.openqa.selenium.By;

public class CheckoutInformationPage extends BasePage {

    private final By checkoutInformationContainer =
            By.className("checkout_info");

    public boolean isDisplayed() {
        return isDisplayed(checkoutInformationContainer);
    }
}