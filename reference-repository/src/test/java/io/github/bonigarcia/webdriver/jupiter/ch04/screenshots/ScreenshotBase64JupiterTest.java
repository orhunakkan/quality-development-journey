package io.github.bonigarcia.webdriver.jupiter.ch04.screenshots;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class ScreenshotBase64JupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

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
    void testScreenshotBase64() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        TakesScreenshot ts = (TakesScreenshot) driver;

        String screenshot = ts.getScreenshotAs(OutputType.BASE64);
        log.debug("Screenshot in base64 "
                + "(you can copy and paste it into a browser navigation bar to watch it)\n"
                + "data:image/png;base64,{}", screenshot);
        assertThat(screenshot).isNotEmpty();
    }

}
