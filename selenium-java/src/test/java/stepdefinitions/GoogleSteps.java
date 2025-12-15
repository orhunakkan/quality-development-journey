package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GooglePage;
import utilities.DriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleSteps {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.createChromeDriver();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver(driver);
    }

    @Given("I navigate to Google homepage")
    public void iNavigateToGoogleHomepage() {
        // Use /ncr to avoid regional redirects that can change title/localization
        driver.get("https://www.google.com/ncr");
    }

    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedTitle) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains(expectedTitle));

        String title = driver.getTitle();
        assertNotNull(title, "Page title should not be null");
        assertTrue(title.contains(expectedTitle), "Page title should contain '" + expectedTitle + "'");
    }

    @Then("the search box should be present")
    public void theSearchBoxShouldBePresent() {
        WebElement searchBox = driver.findElement(GooglePage.SEARCH_BOX);
        assertNotNull(searchBox, "Search box should be present");
    }

    @And("the search box should be displayed")
    public void theSearchBoxShouldBeDisplayed() {
        WebElement searchBox = driver.findElement(GooglePage.SEARCH_BOX);
        assertTrue(searchBox.isDisplayed(), "Search box should be displayed");
    }

    @And("the search box should be enabled")
    public void theSearchBoxShouldBeEnabled() {
        WebElement searchBox = driver.findElement(GooglePage.SEARCH_BOX);
        assertTrue(searchBox.isEnabled(), "Search box should be enabled");
    }
}
