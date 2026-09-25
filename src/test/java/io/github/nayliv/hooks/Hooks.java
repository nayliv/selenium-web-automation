package io.github.nayliv.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.nayliv.driver.BrowserType;
import io.github.nayliv.driver.DriverFactory;
import io.github.nayliv.driver.DriverManager;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {

        WebDriver driver =
                DriverFactory.createDriver(BrowserType.CHROME);

        DriverManager.setDriver(driver);

        DriverManager.getDriver()
                .manage()
                .window()
                .maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}