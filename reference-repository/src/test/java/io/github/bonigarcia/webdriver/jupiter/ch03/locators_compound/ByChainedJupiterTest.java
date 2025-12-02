package io.github.bonigarcia.webdriver.jupiter.ch03.locators_compound;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;

import io.github.bonigarcia.wdm.WebDriverManager;

class ByChainedJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testByChained() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        List<WebElement> rowsInForm = driver.findElements(
                new ByChained(By.tagName("form"), By.className("row")));
        assertThat(rowsInForm.size()).isEqualTo(1);
    }

}
