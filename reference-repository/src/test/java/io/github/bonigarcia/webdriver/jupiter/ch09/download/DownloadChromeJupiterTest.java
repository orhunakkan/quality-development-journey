package io.github.bonigarcia.webdriver.jupiter.ch09.download;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class DownloadChromeJupiterTest {

    WebDriver driver;

    File targetFolder;

    @BeforeEach
    void setup() {
        targetFolder = new File(System.getProperty("user.home"), "Downloads");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", targetFolder.toString());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = WebDriverManager.chromedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testDownloadChrome() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/download.html");

        driver.findElement(By.xpath("(//a)[2]")).click();
        driver.findElement(By.xpath("(//a)[3]")).click();

        ConditionFactory await = Awaitility.await()
                .atMost(Duration.ofSeconds(5));
        File wdmLogo = new File(targetFolder, "webdrivermanager.png");
        await.until(() -> wdmLogo.exists());

        File wdmDoc = new File(targetFolder, "webdrivermanager.pdf");
        await.until(() -> wdmDoc.exists());
    }

}
