package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DriverManager;
import static org.assertj.core.api.Assertions.assertThat;

public class ExpandTestingInputTest {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.createChromeDriver();
        driver.get("https://practice.expandtesting.com/inputs");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testExpandTestingInput() {
        WebElement displayInputsButton = driver.findElement(By.id("btn-display-inputs"));
        WebElement clearButton = driver.findElement(By.id("btn-clear-inputs"));
        assertThat(driver.getTitle().contains("inputs"));
        assertThat(displayInputsButton.isDisplayed()).isTrue();
        assertThat(clearButton.isDisplayed()).isTrue();
    }
}