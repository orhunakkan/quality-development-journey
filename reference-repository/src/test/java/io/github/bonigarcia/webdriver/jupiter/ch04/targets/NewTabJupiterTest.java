package io.github.bonigarcia.webdriver.jupiter.ch04.targets;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import io.github.bonigarcia.wdm.WebDriverManager;

class NewTabJupiterTest {

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
    void testNewTab() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles().size()).isEqualTo(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles().size()).isEqualTo(1);
    }

}
