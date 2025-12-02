package io.github.bonigarcia.webdriver.jupiter.ch03.locators;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class ByCssSelectorJupiterTest {

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
    void teardown() {
        driver.quit();
    }

    @Test
    void testByCssSelectorBasic() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement hidden = driver
                .findElement(By.cssSelector("input[type=hidden]"));
        assertThat(hidden.isDisplayed()).isFalse();
    }

    @Test
    void testByCssSelectorAdvanced() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement checkbox1 = driver
                .findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox1.getDomProperty("id")).isEqualTo("my-check-1");
        assertThat(checkbox1.isSelected()).isTrue();

        WebElement checkbox2 = driver
                .findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox2.getDomProperty("id")).isEqualTo("my-check-2");
        assertThat(checkbox2.isSelected()).isFalse();
    }

}
