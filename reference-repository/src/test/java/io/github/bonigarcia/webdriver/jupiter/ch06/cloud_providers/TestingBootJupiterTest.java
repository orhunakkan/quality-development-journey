package io.github.bonigarcia.webdriver.jupiter.ch06.cloud_providers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

class TestingBootJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        String key = System.getProperty("testingBootUsername");
        String secret = System.getProperty("testingBootAccessKey");

        // An alternative way to read key and secret is using envs:
        // String key = System.getenv("TESTINGBOOT_KEY");
        // String secret = System.getenv("TESTINGBOOT_SECRET");

        assumeThat(key).isNotEmpty();
        assumeThat(secret).isNotEmpty();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("platform", Platform.WINDOWS);
        capabilities.setCapability("name", "Testing Selenium");

        URL remoteUrl = URI.create(String
                .format("http://%s:%s@hub.testingbot.com/wd/hub", key, secret)).toURL();
        driver = new RemoteWebDriver(remoteUrl, capabilities);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testTestingBoot() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
