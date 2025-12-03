package io.github.bonigarcia.webdriver.jupiter.ch05.caps.extensions;

import static io.github.bonigarcia.wdm.WebDriverManager.zipFolder;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoadExtensionFirefoxJupiterTest {

    WebDriver driver;

    Path zippedExtension;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extensionFolder = Paths
                .get(ClassLoader.getSystemResource("web-extension").toURI());
        zippedExtension = zipFolder(extensionFolder);

        driver = WebDriverManager.firefoxdriver().create();
        ((FirefoxDriver) driver).installExtension(zippedExtension, true);
    }

    @AfterEach
    void teardown() throws InterruptedException, IOException {
        Files.delete(zippedExtension);

        driver.quit();
    }

    @Test
    void testExtensions() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertThat(h1.getText())
                .isNotEqualTo("Hands-On Selenium WebDriver with Java");
    }

}
