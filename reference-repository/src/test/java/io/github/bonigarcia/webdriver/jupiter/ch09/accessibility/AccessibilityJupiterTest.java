package io.github.bonigarcia.webdriver.jupiter.ch09.accessibility;

import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

class AccessibilityJupiterTest {

    final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testAccessibility() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");

        Results result = new AxeBuilder().analyze(driver);
        List<Rule> violations = result.getViolations();
        violations.forEach(rule -> {
            log.debug("{}", rule.toString());
        });
        AxeReporter.writeResultsToJsonFile("testAccessibility", result);
    }

}
