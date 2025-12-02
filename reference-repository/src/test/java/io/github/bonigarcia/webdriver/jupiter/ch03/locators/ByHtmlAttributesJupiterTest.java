package io.github.bonigarcia.webdriver.jupiter.ch03.locators;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

class ByHtmlAttributesJupiterTest {

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
    void testByHtmlAttributes() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        // By name
        WebElement textByName = driver.findElement(By.name("my-text"));
        assertThat(textByName.isEnabled()).isTrue();

        // By id
        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getDomAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomProperty("type")).isEqualTo("text");

        assertThat(textById.getDomAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textById.getDomProperty("myprop")).isNull();

        // By class name
        List<WebElement> byClassName = driver
                .findElements(By.className("form-control"));
        assertThat(byClassName.size()).isPositive();
        assertThat(byClassName.get(0).getDomProperty("name"))
                .isEqualTo("my-text");
    }

}
