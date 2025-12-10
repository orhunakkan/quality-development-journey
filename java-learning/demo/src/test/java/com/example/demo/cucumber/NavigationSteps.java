package com.example.demo.cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NavigationSteps {
    private WebDriver driver;

    @Before
    public void startDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        }
    }

    @After
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Given("I open the example page")
    public void iOpenTheExamplePage() {
        driver.get("https://example.com/");
    }

    @Then("the page title contains {string}")
    public void thePageTitleContains(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.equalsIgnoreCase(expectedTitle));
    }
}
