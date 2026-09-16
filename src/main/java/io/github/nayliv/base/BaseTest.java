package io.github.nayliv.base;

import io.github.nayliv.driver.BrowserType;
import io.github.nayliv.driver.DriverFactory;
import io.github.nayliv.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    @BeforeEach
    void setUp() {

        WebDriver driver =
                DriverFactory.createDriver(BrowserType.CHROME);

        DriverManager.setDriver(driver);

        DriverManager.getDriver()
                .manage()
                .window()
                .maximize();
    }

    @AfterEach
    void tearDown() {
        DriverManager.quitDriver();
    }
}