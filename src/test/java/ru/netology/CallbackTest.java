package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldBeSuccessIfDataIsCorrect() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Дарья");
        $("[data-test-id=phone] input").setValue("+79032596295");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        $("[data-test-id=order-success]").should(exist);
    }

    @Test
    void shouldBeErrorIfNameIsEmpty() {
        open("http://localhost:9999");

        $("[data-test-id=phone] input").setValue("+79032596295");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        $("[data-test-id=name]").shouldHave(cssClass("input_invalid"));
    }

    /*@Test
    void shouldBeErrorIfNameIsNotRussian() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Дарья");
        $("[data-test-id=phone] input").setValue("+79032596295");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        $(By.className("input_invalid")).shouldHave(attribute("data-test-id", "name"));
    }*/

    void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
