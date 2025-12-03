package io.github.bonigarcia.webdriver.jupiter.ch03.user_gestures;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

class CopyAndPasteJupiterTest {

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
    void testCopyAndPaste() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Actions actions = new Actions(driver);

        WebElement inputText = driver.findElement(By.name("my-text"));
        WebElement textarea = driver.findElement(By.name("my-textarea"));

        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        actions.sendKeys(inputText, "hello world").keyDown(modifier)
                .sendKeys(inputText, "a").sendKeys(inputText, "c")
                .sendKeys(textarea, "v").build().perform();

        assertThat(inputText.getDomProperty("value"))
                .isEqualTo(textarea.getDomProperty("value"));
    }

}
