package io.github.bonigarcia.webdriver.jupiter.ch09.ab_testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class ABTestingJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testABTesting() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/ab-testing.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement header = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("h6")));

        if (header.getText().contains("variation A")) {
            assertBodyContains(driver, "Lorem ipsum");
        } else if (header.getText().contains("variation B")) {
            assertBodyContains(driver, "Nibh netus");
        } else {
            fail("Unknown variation");
        }
    }

    void assertBodyContains(WebDriver driver, String text) {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(bodyText).contains(text);
    }

}
