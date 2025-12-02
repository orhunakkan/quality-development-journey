package io.github.bonigarcia.webdriver.jupiter.ch06.cloud_providers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class TestiniumJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        String key = System.getProperty("testiniumKey");

        // An alternative way to read the key is using envs:
        // String key = System.getenv("TESTINUM_KEY");

        assumeThat(key).isNotEmpty();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Firefox");
        capabilities.setCapability("browserVersion", "64");
        capabilities.setCapability("platformName", "Linux");
        capabilities.setCapability("takesScreenshot", true);
        capabilities.setCapability("recordsVideo", true);

        capabilities.setCapability("key", key);

        URL remoteUrl = URI.create("http://hub.testinium.io/wd/hub").toURL();
        driver = new RemoteWebDriver(remoteUrl, capabilities);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testTestinium() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
