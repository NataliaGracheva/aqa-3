package ru.netology.app_order;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class AppOrderTest {

    @Test
    void shouldSeeSuccessMessage() {
        open("http://localhost:9999");

        $("span[data-test-id='name'] input").setValue("Вася Пупкин");
        $("span[data-test-id='phone'] input").setValue("+79000000000");
        $("label[data-test-id='agreement']").click();
        $("button").click();

        $("p[data-test-id='order-success']").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldSeeInvalidNameMessage() {
        open("http://localhost:9999");

        $("span[data-test-id='name'] input").setValue("Vasya Pupkin");
        $("span[data-test-id='phone'] input").setValue("+79000000000");
        $("label[data-test-id='agreement']").click();
        $("button").click();

       $("span[data-test-id='name'] span.input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldSeeEmptyNameFieldMessage() {
        open("http://localhost:9999");

        $("span[data-test-id='name'] input").setValue("");
        $("span[data-test-id='phone'] input").setValue("+79000000000");
        $("label[data-test-id='agreement']").click();
        $("button").click();

        $("span[data-test-id='name'] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldSeeInvalidPhoneMessage() {
        open("http://localhost:9999");

        $("span[data-test-id='name'] input").setValue("Вася Пупкин");
        $("span[data-test-id='phone'] input").setValue("79000000000");
        $("label[data-test-id='agreement']").click();
        $("button").click();

        $("span[data-test-id='phone'] span.input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldSeeEmptyPhoneFieldMessage() {
        open("http://localhost:9999");

        $("span[data-test-id='name'] input").setValue("Вася Пупкин");
        $("span[data-test-id='phone'] input").setValue("");
        $("label[data-test-id='agreement']").click();
        $("button").click();

        $("span[data-test-id='phone'] span.input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
