package io.github.bonigarcia.webdriver.jupiter.ch08.disabled;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.condition.JRE.JAVA_17;
import static org.junit.jupiter.api.condition.OS.MAC;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class DisabledJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Disabled("Optional reason for disabling")
    @Test
    public void testDisabled1() {
        checkPracticeSite();
    }

    @DisabledOnJre(JAVA_17)
    @Test
    public void testDisabled2() {
        checkPracticeSite();
    }

    @EnabledOnOs(MAC)
    @Test
    public void testDisabled3() {
        checkPracticeSite();
    }

    private void checkPracticeSite() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
