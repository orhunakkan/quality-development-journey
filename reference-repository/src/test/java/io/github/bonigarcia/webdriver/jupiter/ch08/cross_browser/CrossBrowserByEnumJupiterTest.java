package io.github.bonigarcia.webdriver.jupiter.ch08.cross_browser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

class CrossBrowserByEnumJupiterTest {

    WebDriver driver;

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @ParameterizedTest
    @EnumSource(value = DriverManagerType.class, names = { "CHROME", "EDGE",
            "FIREFOX" })
    void testCrossBrowserByEnum(DriverManagerType driverManagerType) {
        driver = WebDriverManager.getInstance(driverManagerType).create();

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
