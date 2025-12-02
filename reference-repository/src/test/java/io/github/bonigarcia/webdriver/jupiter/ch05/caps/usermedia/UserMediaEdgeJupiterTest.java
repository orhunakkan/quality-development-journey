package io.github.bonigarcia.webdriver.jupiter.ch05.caps.usermedia;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.regex.Pattern;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class UserMediaEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testUserMedia() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/get-user-media.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("start")).click();
        By videoDevice = By.id("video-device");
        Pattern nonEmptyString = Pattern.compile(".+");
        wait.until(ExpectedConditions.textMatches(videoDevice, nonEmptyString));
        assertThat(driver.findElement(videoDevice).getText()).isNotEmpty();
    }

}
