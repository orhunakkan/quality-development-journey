package io.github.bonigarcia.webdriver.jupiter.ch05.bidi;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.events.CdpEventTypes;
import org.openqa.selenium.logging.HasLogEvents;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class ConsoleEventsChromeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testConsoleEvents() throws InterruptedException {
        HasLogEvents logger = (HasLogEvents) driver;

        CountDownLatch latch = new CountDownLatch(4);
        logger.onLogEvent(CdpEventTypes.consoleEvent(consoleEvent -> {
            log.debug("{} {}: {}", consoleEvent.getTimestamp(),
                    consoleEvent.getType(), consoleEvent.getMessages());
            latch.countDown();
        }));

        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");

        assertThat(latch.await(10, TimeUnit.SECONDS)).isTrue();
    }

}
