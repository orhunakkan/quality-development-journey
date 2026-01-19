package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.ExpandTestingInputTestPage;
import utilities.DriverManager;
import utilities.ConfigManager;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpandTestingInputTest {

    private WebDriver driver;
    private ExpandTestingInputTestPage inputTestPage;

    @Before
    public void setup() {
        driver = DriverManager.createChromeDriver();
        inputTestPage = new ExpandTestingInputTestPage(driver);
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I navigate to the ExpandTesting inputs page")
    public void navigateToInputsPage() {
        driver.get(ConfigManager.getProperty("ENV") + "/inputs");
    }

    @Then("the page title should contain {string}")
    public void verifyPageTitle(String expectedText) {
        assertThat(driver.getTitle().contains(expectedText)).isTrue();
    }

    @Then("the Display Inputs button should be displayed")
    public void verifyDisplayInputsButtonIsDisplayed() {
        assertThat(inputTestPage.getDisplayInputsButton().isDisplayed()).isTrue();
    }

    @Then("the Clear button should be displayed")
    public void verifyClearButtonIsDisplayed() {
        assertThat(inputTestPage.getClearButton().isDisplayed()).isTrue();
    }
}
