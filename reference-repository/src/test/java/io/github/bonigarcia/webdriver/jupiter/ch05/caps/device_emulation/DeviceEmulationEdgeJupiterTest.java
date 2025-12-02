package io.github.bonigarcia.webdriver.jupiter.ch05.caps.device_emulation;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class DeviceEmulationEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);
        deviceMetrics.put("touch", true);
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent",
                "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) "
                        + "AppleWebKit/535.19 (KHTML, like Gecko) "
                        + "Chrome/18.0.1025.166 Mobile Safari/535.19");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testDeviceEmulation() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

}
