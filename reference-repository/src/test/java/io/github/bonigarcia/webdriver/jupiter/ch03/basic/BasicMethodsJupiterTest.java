package io.github.bonigarcia.webdriver.jupiter.ch03.basic;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class BasicMethodsJupiterTest {

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
    void testBasicMethods() {
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);

        assertThat(driver.getTitle())
                .isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(driver.getCurrentUrl()).isEqualTo(sutUrl);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");
    }

}
