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
import org.openqa.selenium.devtools.v142.network.model.BlockedReason;
import org.slf4j.Logger;

import com.google.common.collect.ImmutableList;

import io.github.bonigarcia.wdm.WebDriverManager;

class BlockUrlJupiterTest {

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
    void testBlockUrl() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));

        String urlToBlock = "https://bonigarcia.dev/selenium-webdriver-java/img/hands-on-icon.png";
        devTools.send(Network.setBlockedURLs(ImmutableList.of(urlToBlock)));

        devTools.addListener(Network.loadingFailed(), loadingFailed -> {
            BlockedReason reason = loadingFailed.getBlockedReason().get();
            log.debug("Blocking reason: {}", reason);
            assertThat(reason).isEqualTo(BlockedReason.INSPECTOR);
        });

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
