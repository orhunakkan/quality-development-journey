package io.github.bonigarcia.webdriver.jupiter.ch06.grid;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.grid.Main;
import org.openqa.selenium.net.PortProber;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class StandaloneRemoteJupiterTest {

    WebDriver driver;

    static URL seleniumServerUrl;

    @BeforeAll
    static void setupAll() throws MalformedURLException {
        int port = PortProber.findFreePort();
        WebDriverManager.chromedriver().setup();
        Main.main(
                new String[] { "standalone", "--port", String.valueOf(port) });

        seleniumServerUrl = URI.create(
                String.format("http://localhost:%d/", port)).toURL();
    }

    @BeforeEach
    void setup() {
        driver = new RemoteWebDriver(seleniumServerUrl, new ChromeOptions());
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testStandaloneRemote() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
