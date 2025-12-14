import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class ChromeTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGooglePageTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();

        assertNotNull(title, "Page title should not be null");
        assertTrue(title.contains("Google"), "Page title should contain 'Google'");
    }

    @Test
    public void testGoogleSearchBox() {
        driver.get("https://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));

        assertNotNull(searchBox, "Search box should be present");
        assertTrue(searchBox.isDisplayed(), "Search box should be displayed");
        assertTrue(searchBox.isEnabled(), "Search box should be enabled");
    }
}
