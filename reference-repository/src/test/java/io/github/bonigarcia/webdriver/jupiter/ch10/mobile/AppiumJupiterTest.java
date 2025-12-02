package io.github.bonigarcia.webdriver.jupiter.ch10.mobile;

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
import org.openqa.selenium.remote.CapabilityType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.EspressoOptions;

class AppiumJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        URL appiumServerUrl = URI.create("http://localhost:4723").toURL();
        assumeThat(isOnline(URI.create(appiumServerUrl.toString() + "/status").toURL())).isTrue();

        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        options.setCapability(EspressoOptions.DEVICE_NAME_OPTION,
                "Nexus 5 API 30");
        options.setCapability(EspressoOptions.AUTOMATION_NAME_OPTION,
                "UiAutomator2");

        driver = new AppiumDriver(appiumServerUrl, options);
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testAppium() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
