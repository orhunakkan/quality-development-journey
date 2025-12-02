package io.github.bonigarcia.webdriver.jupiter.ch04.dropdown;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

class SelectJupiterTest {

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
    void test() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        Select select = new Select(driver.findElement(By.name("my-select")));
        String optionLabel = "Three";
        select.selectByVisibleText(optionLabel);

        assertThat(select.getFirstSelectedOption().getText())
                .isEqualTo(optionLabel);
    }

}
