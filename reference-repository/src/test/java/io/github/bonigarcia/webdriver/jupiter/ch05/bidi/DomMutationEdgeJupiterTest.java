package io.github.bonigarcia.webdriver.jupiter.ch05.bidi;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.events.CdpEventTypes;
import org.openqa.selenium.devtools.events.DomMutationEvent;
import org.openqa.selenium.logging.HasLogEvents;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class DomMutationEdgeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.edgedriver().create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testDomMutation() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        HasLogEvents logger = (HasLogEvents) driver;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        AtomicReference<DomMutationEvent> seen = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);
        logger.onLogEvent(CdpEventTypes.domMutation(mutation -> {
            seen.set(mutation);
            latch.countDown();
        }));

        WebElement img = driver.findElement(By.tagName("img"));
        String newSrc = "img/award.png";
        String script = String.format("arguments[0].src = '%s';", newSrc);
        js.executeScript(script, img);

        assertThat(latch.await(10, TimeUnit.SECONDS)).isTrue();
        assertThat(seen.get().getElement().getDomProperty("src"))
                .endsWith(newSrc);
    }

}
