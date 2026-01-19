package e2e;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ExpandTestingInputTestPage;
import utilities.DriverManager;
import utilities.ConfigManager;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpandTestingInputTest {

    private WebDriver driver;
    private ExpandTestingInputTestPage inputTestPage;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.createChromeDriver();
        inputTestPage = new ExpandTestingInputTestPage(driver);
        driver.get(ConfigManager.getProperty("ENV") + "/inputs");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testExpandTestingInput() {
        assertThat(driver.getTitle().contains("inputs"));
        assertThat(inputTestPage.getDisplayInputsButton().isDisplayed()).isTrue();
        assertThat(inputTestPage.getClearButton().isDisplayed()).isTrue();
    }
}