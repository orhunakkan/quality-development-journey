package io.github.bonigarcia.webdriver.jupiter.ch05.caps.incognito;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class IncognitoEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito");

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testIncognito() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
