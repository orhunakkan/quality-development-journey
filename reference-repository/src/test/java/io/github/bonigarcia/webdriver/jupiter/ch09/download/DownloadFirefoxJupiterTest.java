package io.github.bonigarcia.webdriver.jupiter.ch09.download;

import java.io.File;
import java.time.Duration;

import org.awaitility.Awaitility;
import org.awaitility.core.ConditionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class DownloadFirefoxJupiterTest {

        WebDriver driver;

        File targetFolder;

        @BeforeEach
        void setup() {
                FirefoxOptions options = new FirefoxOptions();
                targetFolder = new File(".");
                options.addPreference("browser.download.dir",
                                targetFolder.getAbsolutePath());
                options.addPreference("browser.download.folderList", 2);
                options.addPreference("browser.helperApps.neverAsk.saveToDisk",
                                "image/png, application/pdf");
                options.addPreference("pdfjs.disabled", true);

                driver = WebDriverManager.firefoxdriver().capabilities(options)
                                .create();
        }

        @AfterEach
        void teardown() {
                driver.quit();
        }

        @Test
        void testDownloadFirefox() {
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
                await.await();
        }

}
