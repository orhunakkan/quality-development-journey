package io.github.bonigarcia.webdriver.jupiter.ch05.caps.extensions;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.not;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class AddExtensionFirefoxJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extension = Paths
                .get(ClassLoader.getSystemResource("dark-bg.xpi").toURI());
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(extension.toFile());
        options.setProfile(profile);

        driver = WebDriverManager.firefoxdriver().capabilities(options)
                .create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testAddExtension() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement body = driver.findElement(By.tagName("body"));
        String whiteRgba = "rgba(255, 255, 255, 1)";
        wait.until(not(attributeToBe(body, "background-color", whiteRgba)));
    }

}
