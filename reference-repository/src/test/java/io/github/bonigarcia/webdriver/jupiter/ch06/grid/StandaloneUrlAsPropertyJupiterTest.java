package io.github.bonigarcia.webdriver.jupiter.ch06.grid;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterAll;
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

class StandaloneUrlAsPropertyJupiterTest {

    WebDriver driver;

    @BeforeAll
    static void setupAll() {
        int port = PortProber.findFreePort();
        WebDriverManager.chromedriver().setup();
        Main.main(
                new String[] { "standalone", "--port", String.valueOf(port) });

        System.setProperty("webdriver.remote.server",
                String.format("http://localhost:%d/", port));
    }

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(options);
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @AfterAll
    static void teardownClass() {
        System.clearProperty("webdriver.remote.server");
    }

    @Test
    void testStandaloneUrlAsProperty() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
