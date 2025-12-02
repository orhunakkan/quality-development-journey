package io.github.bonigarcia.webdriver.jupiter.ch03.user_gestures;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

class DragAndDropJupiterTest {

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
    void testDragAndDrop() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions actions = new Actions(driver);

        WebElement draggable = driver.findElement(By.id("draggable"));
        int offset = 100;
        Point initLocation = draggable.getLocation();
        actions.dragAndDropBy(draggable, offset, 0)
                .dragAndDropBy(draggable, 0, offset)
                .dragAndDropBy(draggable, -offset, 0)
                .dragAndDropBy(draggable, 0, -offset).build().perform();
        assertThat(initLocation).isEqualTo(draggable.getLocation());

        WebElement target = driver.findElement(By.id("target"));
        actions.dragAndDrop(draggable, target).build().perform();
        assertThat(target.getLocation()).isEqualTo(draggable.getLocation());
    }

}
