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

class BrowserStackJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        String username = System.getProperty("browserStackUsername");
        String accessKey = System.getProperty("browserStackAccessKey");

        // An alternative way to read username and key is using envs:
        // String username = System.getenv("BROWSERSTACK_USERNAME");
        // String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        assumeThat(username).isNotEmpty();
        assumeThat(accessKey).isNotEmpty();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "64");
        capabilities.setCapability("platformName", "Linux");
        capabilities.setCapability("name", "My test name");
        capabilities.setCapability("build", "My build name");

        URL remoteUrl = URI.create(
                String.format("http://%s:%s@hub-cloud.browserstack.com/wd/hub",
                        username, accessKey))
                .toURL();
        driver = new RemoteWebDriver(remoteUrl, capabilities);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testBrowserStack() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
