package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CallbackTest {

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

        $("[data-test-id=name] input").setValue("Дарья");
        $("[data-test-id=agreement]").click();
        $(By.tagName("button")).click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.shouldHave(cssClass("input_invalid"));
        phone.$(By.className("input__sub")).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldBeErrorIfAgreementIsEmpty() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Дарья");
        $("[data-test-id=phone] input").setValue("+79032596295");
        $(By.tagName("button")).click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}
