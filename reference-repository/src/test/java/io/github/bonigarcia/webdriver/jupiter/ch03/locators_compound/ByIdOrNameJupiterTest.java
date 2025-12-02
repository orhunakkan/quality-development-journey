package io.github.bonigarcia.webdriver.jupiter.ch03.locators_compound;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;

import io.github.bonigarcia.wdm.WebDriverManager;

class ByIdOrNameJupiterTest {

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
    void testByIdOrName() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement fileElement = driver.findElement(new ByIdOrName("my-file"));
        assertThat(fileElement.getDomProperty("id")).isBlank();
        assertThat(fileElement.getDomProperty("name")).isNotBlank();
    }

}
