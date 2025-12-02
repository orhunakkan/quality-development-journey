package io.github.bonigarcia.webdriver.jupiter.ch04.cookies;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class AddCookiesJupiterTest {

    WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testAddCookies() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        Options options = driver.manage();
        Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();
        assertThat(newCookie.getValue()).isEqualTo(readValue);

        driver.findElement(By.id("refresh-cookies")).click();
    }

}
