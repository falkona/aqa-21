package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.ExactText;
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
        SelenideElement name = $("[data-test-id=name]");
        name.shouldHave(cssClass("input_invalid"));
        name.$(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeErrorIfNameIsNotRussian() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Daria");
        $("[data-test-id=phone] input").setValue("+79032596295");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        SelenideElement name = $("[data-test-id=name]");
        name.shouldHave(cssClass("input_invalid"));
        name.$(By.className("input__sub")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldBeErrorIfPhoneIsEmpty() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Daria");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.shouldHave(cssClass("input_invalid"));
        phone.$(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
