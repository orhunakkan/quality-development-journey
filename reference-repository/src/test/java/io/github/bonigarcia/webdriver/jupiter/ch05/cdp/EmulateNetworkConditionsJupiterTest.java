package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;
import org.openqa.selenium.devtools.v142.network.model.ConnectionType;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class EmulateNetworkConditionsJupiterTest {

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
    void teardown() {
        devTools.close();
        driver.quit();
    }

    @SuppressWarnings("deprecation")
    @Test
    void testEmulateNetworkConditions() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 100, 50 * 1024,
                50 * 1024, Optional.of(ConnectionType.CELLULAR3G),
                Optional.empty(), Optional.empty(), Optional.empty()));

        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration
                .ofMillis(System.currentTimeMillis() - initMillis);
        log.debug("The page took {} ms to be loaded", elapsed.toMillis());

        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
