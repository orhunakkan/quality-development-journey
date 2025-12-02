package io.github.bonigarcia.webdriver.jupiter.ch04.timeouts;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class ScriptTimeoutJupiterTest {

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
    void testScriptTimeout() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(3));

        assertThatThrownBy(() -> {
            long waitMillis = Duration.ofSeconds(5).toMillis();
            String script = "const callback = arguments[arguments.length - 1];"
                    + "window.setTimeout(callback, " + waitMillis + ");";
            js.executeAsyncScript(script);
        }).isInstanceOf(ScriptTimeoutException.class);
    }

}
