package com.example.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExamplePage {
    private static final String URL = "https://example.com";

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(tagName = "h1")
    private WebElement heading;

    @FindBy(tagName = "p")
    private WebElement paragraph;

    public ExamplePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getHeadingText() {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }

    public String getParagraphText() {
        return wait.until(ExpectedConditions.visibilityOf(paragraph)).getText();
    }
}
