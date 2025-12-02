package io.github.bonigarcia.webdriver.jupiter.ch04.javascript;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

class ScrollIntoViewJupiterTest {

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
    void testScrollIntoView() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement lastElememt = driver
                .findElement(By.cssSelector("p:last-child"));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastElememt);
    }

}
