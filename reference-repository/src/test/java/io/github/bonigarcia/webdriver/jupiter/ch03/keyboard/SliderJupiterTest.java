package io.github.bonigarcia.webdriver.jupiter.ch03.keyboard;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class SliderJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

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
    void testSlider() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement slider = driver.findElement(By.name("my-range"));
        String initValue = slider.getDomProperty("value");
        log.debug("The initial value of the slider is {}", initValue);

        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = slider.getDomProperty("value");
        log.debug("The final value of the slider is {}", endValue);
        assertThat(initValue).isNotEqualTo(endValue);
    }

}
