package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;
import org.openqa.selenium.devtools.v142.network.model.Cookie;
import org.openqa.selenium.devtools.v142.storage.Storage;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class ManageCookiesJupiterTest {

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
        void testManageCookies() {
                devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                                Optional.empty(), Optional.empty(), Optional.empty()));
                driver.get(
                                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

                // Read cookies
                List<Cookie> cookies = devTools
                                .send(Storage.getCookies(Optional.empty()));
                cookies.forEach(cookie -> log.debug("{}={}", cookie.getName(),
                                cookie.getValue()));
                List<String> cookieName = cookies.stream()
                                .map(cookie -> cookie.getName()).sorted()
                                .collect(Collectors.toList());
                Set<org.openqa.selenium.Cookie> seleniumCookie = driver.manage()
                                .getCookies();
                List<String> selCookieName = seleniumCookie.stream()
                                .map(selCookie -> selCookie.getName()).sorted()
                                .collect(Collectors.toList());
                assertThat(cookieName).isEqualTo(selCookieName);

                // Clear cookies
                devTools.send(Network.clearBrowserCookies());
                List<Cookie> cookiesAfterClearing = devTools
                                .send(Storage.getCookies(Optional.empty()));
                assertThat(cookiesAfterClearing).isEmpty();

                driver.findElement(By.id("refresh-cookies")).click();
        }
}
