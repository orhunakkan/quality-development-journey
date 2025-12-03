package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class DeviceEmulationJupiterTest {

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
    void testDeviceEmulation() {
        // 1. Override user agent (Apple iPhone 6)
        String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) "
                + "AppleWebKit/600.1.3 (KHTML, like Gecko) "
                + "Version/8.0 Mobile/12A4345d Safari/600.1.4";
        devTools.send(Network.setUserAgentOverride(userAgent, Optional.empty(),
                Optional.empty(), Optional.empty()));

        // 2. Emulate device dimension
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 375);
        deviceMetrics.put("height", 667);
        deviceMetrics.put("mobile", true);
        deviceMetrics.put("deviceScaleFactor", 2);
        ((ChromeDriver) driver).executeCdpCommand(
                "Emulation.setDeviceMetricsOverride", deviceMetrics);

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
