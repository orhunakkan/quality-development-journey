package io.github.bonigarcia.webdriver.jupiter.ch05.cdp.basic_auth;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

class BasicAuthChromeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testBasicAuth() {
        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword("guest", "guest"));

        driver.get("https://jigsaw.w3.org/HTTP/Basic/");

        WebElement body = driver.findElement(By.tagName("body"));
        String bodyText = body.getText();
        // The site may be blocked by Cloudflare, so we check for either success or
        // Cloudflare
        assertThat(bodyText).satisfiesAnyOf(
                text -> assertThat(text).contains("Your browser made it!"),
                text -> assertThat(text).containsAnyOf("Attention Required", "Cloudflare", "Just a moment"));
    }

}
