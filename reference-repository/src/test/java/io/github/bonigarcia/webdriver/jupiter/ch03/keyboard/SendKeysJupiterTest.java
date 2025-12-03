package io.github.bonigarcia.webdriver.jupiter.ch03.keyboard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

class SendKeysJupiterTest {

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
    void testSendKeys() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement inputText = driver.findElement(By.name("my-text"));
        String textValue = "Hello World!";
        inputText.sendKeys(textValue);
        assertThat(inputText.getDomProperty("value")).isEqualTo(textValue);

        inputText.clear();
        assertThat(inputText.getDomProperty("value")).isEmpty();
    }

}
