package io.github.bonigarcia.webdriver.jupiter.ch05.caps.insecure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.Color;

import io.github.bonigarcia.wdm.WebDriverManager;

class InsecureFirefoxJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(true);

        driver = WebDriverManager.firefoxdriver().capabilities(options)
                .create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testInsecure() {
        driver.get("https://untrusted-root.badssl.com/");

        String bgColor = driver.findElement(By.tagName("body"))
                .getCssValue("background-color");
        Color red = new Color(255, 0, 0, 1);
        assertThat(Color.fromString(bgColor)).isEqualTo(red);
    }

}
