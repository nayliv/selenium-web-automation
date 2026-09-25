package io.github.nayliv.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.nayliv.config.ConfigManager;
import io.github.nayliv.driver.DriverFactory;
import io.github.nayliv.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver(
                ConfigManager.getBrowser()
        );

        DriverManager.setDriver(driver);

        driver.manage()
                .window()
                .maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Validation - " + scenario.getName()
            );

        } finally {
            DriverManager.quitDriver();
        }
    }
}