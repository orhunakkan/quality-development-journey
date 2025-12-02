package io.github.bonigarcia.webdriver.jupiter.ch09.spring;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.webdriver.SpringBootDemoApp;

@Disabled("Not compatible with logback beta")
@SpringBootTest(classes = SpringBootDemoApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootJupiterTest {

    private WebDriver driver;

    @LocalServerPort
    protected int serverPort;

    @BeforeEach
    void setup() {
        driver = WebDriverManager.chromedriver().create();
    }

    @After
    void teardown() {
        driver.quit();
    }

    @Test
    void testSpringBoot() {
        driver.get("http://localhost:" + serverPort);
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(bodyText)
                .contains("This is a local site served by Spring-Boot");
    }

}
