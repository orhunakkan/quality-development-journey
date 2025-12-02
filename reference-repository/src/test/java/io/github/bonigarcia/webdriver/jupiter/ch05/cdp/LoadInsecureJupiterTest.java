package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.security.Security;
import org.openqa.selenium.support.Color;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoadInsecureJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    DevTools devTools;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        devTools.close();
        driver.quit();
    }

    @Test
    void testLoadInsecure() {
        devTools.send(Security.enable());
        devTools.send(Security.setIgnoreCertificateErrors(true));
        driver.get("https://expired.badssl.com/");

        String bgColor = driver.findElement(By.tagName("body"))
                .getCssValue("background-color");
        Color red = new Color(255, 0, 0, 1);
        assertThat(Color.fromString(bgColor)).isEqualTo(red);
    }
}
