package com.example.demo.cucumber;

import com.example.demo.pages.ExamplePage;
import com.example.demo.utils.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class NavigationSteps {
    private WebDriver driver;
    private ExamplePage examplePage;

    @Before
    public void startDriver() {
        driver = DriverFactory.getDriver();
        examplePage = new ExamplePage(driver);
    }

    @After
    public void stopDriver() {
        DriverFactory.quitDriver();
    }

    @Given("I open the example page")
    public void iOpenTheExamplePage() {
        examplePage.open();
    }

    @When("I navigate to example domain")
    public void iNavigateToExampleDomain() {
        examplePage.open();
    }

    @Then("the page title contains {string}")
    public void thePageTitleContains(String expectedTitle) {
        String actualTitle = examplePage.getPageTitle();
        assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()));
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        assertEquals(expectedTitle, examplePage.getPageTitle());
    }

    @Then("the heading text should be {string}")
    public void theHeadingTextShouldBe(String expectedHeading) {
        assertEquals(expectedHeading, examplePage.getHeadingText());
    }
}
