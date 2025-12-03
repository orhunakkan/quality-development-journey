package io.github.bonigarcia.webdriver.jupiter.ch05.caps.binary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class BinaryFirefoxJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        Path browserBinary = Paths.get(
                "C:\\Program Files\\Firefox Developer Edition\\firefox.exe");
        assumeThat(browserBinary).exists();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(browserBinary);
        driver = WebDriverManager.firefoxdriver().capabilities(options)
                .create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void testBinary() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
