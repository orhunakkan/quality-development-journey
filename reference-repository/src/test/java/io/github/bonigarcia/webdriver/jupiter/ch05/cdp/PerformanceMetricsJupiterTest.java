package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.performance.Performance;
import org.openqa.selenium.devtools.v142.performance.model.Metric;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class PerformanceMetricsJupiterTest {

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
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        devTools.close();
        driver.quit();
    }

    @Test
    void testPerformanceMetrics() {
        devTools.send(Performance.enable(Optional.empty()));
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<Metric> metrics = devTools.send(Performance.getMetrics());
        assertThat(metrics).isNotEmpty();
        metrics.forEach(metric -> log.debug("{}: {}", metric.getName(),
                metric.getValue()));
    }
}
