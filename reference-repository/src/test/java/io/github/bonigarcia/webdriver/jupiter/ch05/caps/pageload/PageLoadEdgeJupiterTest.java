package io.github.bonigarcia.webdriver.jupiter.ch05.caps.pageload;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class PageLoadEdgeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    PageLoadStrategy pageLoadStrategy;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        pageLoadStrategy = PageLoadStrategy.NONE;
        options.setPageLoadStrategy(pageLoadStrategy);

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testPageLoad() {
        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration
                .ofMillis(System.currentTimeMillis() - initMillis);

        Capabilities capabilities = ((RemoteWebDriver) driver)
                .getCapabilities();
        Object pageLoad = capabilities
                .getCapability(CapabilityType.PAGE_LOAD_STRATEGY);
        String browserName = capabilities.getBrowserName();
        log.debug(
                "The page took {} ms to be loaded using a '{}' strategy in {}",
                elapsed.toMillis(), pageLoad, browserName);

        assertThat(pageLoad).isEqualTo(pageLoadStrategy.toString());
    }

}
