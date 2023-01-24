package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderCardTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSubmitFormTest() {
        $(".form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldFormTestEmptyName() {

        $(".form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFormTestEmptyPhone() {

        $(".form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Иван");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFormTestInvalidName() {

        $(".form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Ivanov Ivan");
        $("[data-test-id=phone] input").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldFormTestHyphenName() {

        $(".form_theme_alfa-on-white");
        $("[data-test-id=name] input").setValue("Иванов Иван-Тест");
        $("[data-test-id=phone] input").setValue("+79111111111");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

}
