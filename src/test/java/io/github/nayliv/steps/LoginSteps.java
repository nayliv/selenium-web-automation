package io.github.nayliv.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.nayliv.config.ConfigManager;
import io.github.nayliv.driver.DriverManager;
import io.github.nayliv.pages.LoginPage;
import io.github.nayliv.pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        DriverManager.getDriver()
                .get(ConfigManager.getBaseUrl());

        loginPage = new LoginPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(
            String username,
            String password) {

        loginPage.login(username, password);
    }

    @Then("the products page should be displayed")
    public void theProductsPageShouldBeDisplayed() {
        productsPage = new ProductsPage();

        assertTrue(
                productsPage.isDisplayed(),
                "Products page was not displayed after login."
        );
    }

    @Then("the login error message should be {string}")
    public void theLoginErrorMessageShouldBe(String expectedMessage) {
        assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Login error message was not displayed."
        );

        assertEquals(
                expectedMessage,
                loginPage.getErrorMessage()
        );
    }
}