package io.github.bonigarcia.webdriver.jupiter.ch05.caps.l10n;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class LangChromeJupiterTest {

    WebDriver driver;

    String lang;

    @BeforeEach
    void setup() {
        lang = "en-US";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + lang);

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() throws InterruptedException {
        driver.quit();
    }

    @Test
    void testLang() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/multilanguage.html");

        ResourceBundle strings = ResourceBundle.getBundle("strings",
                Locale.forLanguageTag(lang));
        String home = strings.getString("home");
        String content = strings.getString("content");
        String about = strings.getString("about");
        String contact = strings.getString("contact");

        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(bodyText).contains(home).contains(content).contains(about)
                .contains(contact);
    }

}
