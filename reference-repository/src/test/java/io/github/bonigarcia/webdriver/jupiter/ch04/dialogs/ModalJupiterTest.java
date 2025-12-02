package io.github.bonigarcia.webdriver.jupiter.ch04.dialogs;

import static org.assertj.core.api.Assertions.assertThat;

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

class ModalJupiterTest {

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
    void testModal() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("my-modal")).click();
        WebElement close = driver
                .findElement(By.xpath("//button[text() = 'Close']"));
        assertThat(close.getTagName()).isEqualTo("button");
        wait.until(ExpectedConditions.elementToBeClickable(close));
        close.click();
    }

}
