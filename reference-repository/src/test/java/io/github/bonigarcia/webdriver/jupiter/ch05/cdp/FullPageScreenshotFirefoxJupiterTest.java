package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class FullPageScreenshotFirefoxJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.firefoxdriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testFullPageScreenshotFirefox() throws IOException {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                By.className("container"), By.tagName("p")));

        byte[] imageBytes = ((FirefoxDriver) driver)
                .getFullPageScreenshotAs(OutputType.BYTES);
        Path destination = Paths.get("fullpage-screenshot-firefox.png");
        Files.write(destination, imageBytes);

        assertThat(destination).exists();
    }

}
