package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.DriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpandTestingInputTest {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = DriverManager.createChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the ExpandTesting inputs page")
    public void navigateToInputsPage() {
        driver.get("https://practice.expandtesting.com/inputs");
    }

    @Then("the page title should contain {string}")
    public void verifyPageTitle(String expectedText) {
        assertThat(driver.getTitle()).contains(expectedText);
    }

    @Then("the Display Inputs button should be displayed")
    public void verifyDisplayInputsButtonIsDisplayed() {
        WebElement displayInputsButton = driver.findElement(By.id("btn-display-inputs"));
        assertThat(displayInputsButton.isDisplayed()).isTrue();
    }

    @Then("the Clear button should be displayed")
    public void verifyClearButtonIsDisplayed() {
        WebElement clearButton = driver.findElement(By.id("btn-clear-inputs"));
        assertThat(clearButton.isDisplayed()).isTrue();
    }
}
