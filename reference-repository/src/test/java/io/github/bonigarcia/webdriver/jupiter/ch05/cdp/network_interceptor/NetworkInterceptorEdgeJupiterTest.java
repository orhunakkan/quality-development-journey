package io.github.bonigarcia.webdriver.jupiter.ch05.cdp.network_interceptor;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.Contents;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Route;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class NetworkInterceptorEdgeJupiterTest {

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

    @SuppressWarnings("unused")
    @Test
    void testNetworkInterceptor() throws Exception {
        Path img = Paths
                .get(ClassLoader.getSystemResource("tools.png").toURI());
        byte[] bytes = Files.readAllBytes(img);

        try (NetworkInterceptor interceptor = new NetworkInterceptor(driver,
                Route.matching(req -> req.getUri().endsWith(".png"))
                        .to(() -> req -> new HttpResponse()
                                .setContent(Contents.bytes(bytes))))) {
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

            int width = Integer.parseInt(driver.findElement(By.tagName("img"))
                    .getDomProperty("width"));
            assertThat(width).isGreaterThan(80);
        }
    }

}
