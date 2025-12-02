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

class PerfectoJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        String cloudName = System.getProperty("perfectoCloudName");
        String securityToken = System.getProperty("perfectoSecurityToken");

        // An alternative way to read cloudname and token is using envs:
        // String cloudName = System.getenv("PERFECTO_CLOUD_NAME");
        // String securityToken = System.getenv("PERFECTO_SECURITY_TOKEN");

        assumeThat(cloudName).isNotEmpty();
        assumeThat(securityToken).isNotEmpty();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("browserVersion", "latest");
        capabilities.setCapability("platformName", "Windows");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("location", "US East");
        capabilities.setCapability("resolution", "1024x768");
        capabilities.setCapability("securityToken", securityToken);

        URL remoteUrl = URI.create(String.format(
                "http://%s.perfectomobile.com/nexperience/perfectomobile/wd/hub",
                cloudName)).toURL();
        driver = new RemoteWebDriver(remoteUrl, capabilities);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testPerfecto() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
