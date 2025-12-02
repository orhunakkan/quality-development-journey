package io.github.bonigarcia.webdriver.jupiter.ch05.caps.extensions;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;
import org.slf4j.Logger;

import io.github.bonigarcia.wdm.WebDriverManager;

class AddExtensionEdgeJupiterTest {

    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extension = Paths.get(
                ClassLoader.getSystemResource("shade_dark_mode.crx").toURI());
        EdgeOptions options = new EdgeOptions();
        options.addExtensions(extension.toFile());

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testAddExtension() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebElement body = driver.findElement(By.tagName("body"));
        log.debug("Background color is {}"
                + body.getCssValue("background-color"));
    }

}
