package com.example.demo.cucumber;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class NavigationSteps {
    private WebDriver driver;

    @Before
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--window-size=1280,800");
        driver = new ChromeDriver(options);
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
        assertNotNull(driver, "WebDriver should be available for the scenario");
        driver.navigate().to("https://example.org");
    }

    @Then("the page title contains {string}")
    public void thePageTitleContains(String expectedTitle) {
        assertNotNull(driver, "WebDriver should be available for the scenario");
        String actualTitle = driver.getTitle();
        assertTrue(actualTitle.contains(expectedTitle),
                () -> "Expected title to contain '" + expectedTitle + "' but was '" + actualTitle + "'");
    }
}
