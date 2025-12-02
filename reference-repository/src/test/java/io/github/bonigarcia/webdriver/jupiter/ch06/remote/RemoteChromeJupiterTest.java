package io.github.bonigarcia.webdriver.jupiter.ch06.remote;

import static io.github.bonigarcia.wdm.WebDriverManager.isOnline;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

class RemoteChromeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        URL seleniumServerUrl = URI.create("http://localhost:4444/").toURL();
        assumeThat(isOnline(seleniumServerUrl)).isTrue();

        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(seleniumServerUrl, options);

        // Instead of options we can use:

        // DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName("chrome");

        // ... or:

        // capabilities.setCapability(CapabilityType.BROWSER_NAME,
        // Browser.CHROME);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testRemote() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
