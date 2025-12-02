package io.github.bonigarcia.webdriver.jupiter.ch04.dropdown;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

class DataListJupiterTest {

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
    void testDatalist() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.click();

        WebElement option = driver
                .findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getDomProperty("value");
        datalist.sendKeys(optionValue);

        assertThat(optionValue).isEqualTo("New York");
    }

}
