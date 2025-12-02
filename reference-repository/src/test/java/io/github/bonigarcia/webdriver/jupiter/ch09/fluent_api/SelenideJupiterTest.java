package io.github.bonigarcia.webdriver.jupiter.ch09.fluent_api;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

class SelenideJupiterTest {

    @Test
    void testSelenide() {
        open("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");

        $(By.id("username")).val("user");
        $(By.id("password")).val("user");
        $("button").pressEnter();
        $(By.id("success")).shouldBe(visible)
                .shouldHave(text("Login successful"));
    }

}
