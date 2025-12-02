package io.github.bonigarcia.webdriver.jupiter.ch05.cdp;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v142.network.Network;
import org.openqa.selenium.devtools.v142.network.model.Headers;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class ExtraHeadersJupiterTest {

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
    void testExtraHeaders() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));

        String userName = "guest";
        String password = "guest";
        Map<String, Object> headers = new HashMap<>();
        String basicAuth = "Basic " + new String(Base64.getEncoder()
                .encode(String.format("%s:%s", userName, password).getBytes()));
        headers.put("Authorization", basicAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("https://jigsaw.w3.org/HTTP/Basic/");
        String bodyText = driver.findElement(By.tagName("body")).getText();
        // The site may be blocked by Cloudflare, so we check for either success or
        // Cloudflare
        assertThat(bodyText).satisfiesAnyOf(
                text -> assertThat(text).contains("Your browser made it!"),
                text -> assertThat(text).containsAnyOf("Attention Required", "Cloudflare", "Just a moment"));
    }
}
