package io.github.bonigarcia.webdriver.jupiter.ch05.caps.extensions;

import static org.assertj.core.api.Assertions.assertThat;

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

import io.github.bonigarcia.wdm.WebDriverManager;

class LoadExtensionEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() throws URISyntaxException {
        Path extension = Paths
                .get(ClassLoader.getSystemResource("web-extension").toURI());
        EdgeOptions options = new EdgeOptions();
        options.addArguments(
                "--load-extension=" + extension.toAbsolutePath().toString());

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        // FIXME: pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(3).toMillis());

        driver.quit();
    }

    @Test
    void testExtensions() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertThat(h1.getText())
                .isNotEqualTo("Hands-On Selenium WebDriver with Java");
    }

}
