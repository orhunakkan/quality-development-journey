import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.GooglePage;
import utilities.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class ChromeTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.createChromeDriver();
        driver.get("https://www.google.com");
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver(driver);
    }

    @Test
    public void testGooglePageTitle() {
        String title = driver.getTitle();
        assertNotNull(title, "Page title should not be null");
        assertTrue(title.contains("Google"), "Page title should contain 'Google'");
    }

    @Test
    public void testGoogleSearchBox() {
        WebElement searchBox = driver.findElement(GooglePage.SEARCH_BOX);
        assertNotNull(searchBox, "Search box should be present");
        assertTrue(searchBox.isDisplayed(), "Search box should be displayed");
        assertTrue(searchBox.isEnabled(), "Search box should be enabled");
    }
}
