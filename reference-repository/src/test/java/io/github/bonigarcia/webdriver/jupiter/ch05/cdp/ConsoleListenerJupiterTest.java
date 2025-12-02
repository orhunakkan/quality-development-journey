package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.events.ConsoleEvent;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class ConsoleListenerJupiterTest {

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

        @Test
        void testConsoleListener() throws Exception {
                CompletableFuture<ConsoleEvent> futureEvents = new CompletableFuture<>();
                devTools.getDomains().events()
                                .addConsoleListener(futureEvents::complete);

                CompletableFuture<JavascriptException> futureJsExc = new CompletableFuture<>();
                devTools.getDomains().events()
                                .addJavascriptExceptionListener(futureJsExc::complete);

                driver.get(
                                "https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");

                ConsoleEvent consoleEvent = futureEvents.get(5, TimeUnit.SECONDS);
                log.debug("ConsoleEvent: {} {} {}", consoleEvent.getTimestamp(),
                                consoleEvent.getType(), consoleEvent.getMessages());

                JavascriptException jsException = futureJsExc.get(5,
                                TimeUnit.SECONDS);
                log.debug("JavascriptException: {} {}", jsException.getMessage(),
                                jsException.getSystemInformation());
        }
}
