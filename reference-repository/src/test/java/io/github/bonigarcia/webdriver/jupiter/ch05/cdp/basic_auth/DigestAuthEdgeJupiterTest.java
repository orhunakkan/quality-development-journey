package io.github.bonigarcia.webdriver.jupiter.ch05.cdp.basic_auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

@Disabled("Since the online service does not accept many consecutive requests")
class DigestAuthEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.edgedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testDigestAuth() {
        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword("guest", "guest"));

        driver.get("https://jigsaw.w3.org/HTTP/Digest/");

        WebElement body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).contains("Your browser made it!");
    }

}
