package com.example.demo.tests;

import com.example.demo.pages.ExamplePage;
import com.example.demo.utils.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class ExamplePageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @Test
    public void verifyExamplePageTitle() {
        ExamplePage page = new ExamplePage(driver);
        page.open();

        assertEquals("Example Domain", page.getPageTitle());
    }

    @Test
    public void verifyExamplePageHeading() {
        ExamplePage page = new ExamplePage(driver);
        page.open();

        assertEquals("Example Domain", page.getHeadingText());
    }

    @Test
    public void verifyExamplePageContent() {
        ExamplePage page = new ExamplePage(driver);
        page.open();

        String paragraphText = page.getParagraphText();
        assertTrue(paragraphText.contains("illustrative") || paragraphText.contains("example"));
    }
}
