package io.github.bonigarcia.webdriver.jupiter.ch04.targets;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.Set;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class NewTabWithKeysJupiterTest {

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
    void testNewTabWithKeys() {
        String initPage = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(initPage);

        Keys modifier = SystemUtils.IS_OS_MAC ? Keys.COMMAND : Keys.CONTROL;
        String openInNewTab = Keys.chord(modifier, Keys.RETURN);
        driver.findElement(By.linkText("Web form")).sendKeys(openInNewTab);

        Set<String> windowHandles = driver.getWindowHandles();
        assertThat(windowHandles.size()).isEqualTo(2);

        for (String windowHandle : windowHandles) {
            if (driver.getWindowHandle().equals(windowHandle)) {
                log.debug("Current window handle {}", windowHandle);
                assertThat(driver.getCurrentUrl()).isEqualTo(initPage);
            } else {
                log.debug("Switching to window handle {}", windowHandle);
                driver.switchTo().window(windowHandle);
                assertThat(driver.getCurrentUrl()).isNotEqualTo(initPage);
            }
        }

        driver.close();
        assertThat(driver.getWindowHandles().size()).isEqualTo(1);
    }

}
