package ru.yandex.praktikum;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {

    // Вход и регистрация
    private final SelenideElement authButton = $x("//button[contains(text(),'Вход и регистрация')]");

    public void clickAuthButton() {
        authButton.click();
    }

    public void shouldNotSeeAuthButton() {
        authButton.shouldNotBe(visible)
                .shouldNotBe(exist);   // дополнительная проверка
    }

}
