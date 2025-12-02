package io.github.bonigarcia.webdriver.jupiter.ch05.print;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.print.PrintOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

class PrintEdgeJupiterTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");

        driver = WebDriverManager.edgedriver().capabilities(options).create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void testPrint() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        PrintsPage pg = (PrintsPage) driver;
        PrintOptions printOptions = new PrintOptions();
        Pdf pdf = pg.print(printOptions);

        String pdfBase64 = pdf.getContent();
        assertThat(pdfBase64).contains("JVBER");

        byte[] decodedImg = Base64.getDecoder()
                .decode(pdfBase64.getBytes(StandardCharsets.UTF_8));
        Path destinationFile = Paths.get("my-pdf.pdf");
        Files.write(destinationFile, decodedImg);
    }

}
