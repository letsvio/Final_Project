package ru.yandex.praktikum;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class RegisterPage {
    // Email
    private final SelenideElement emailInput = $x("//input[contains(@placeholder,'Введите Email')]");

    // Пароль
    private final SelenideElement passwordInput = $x("//input[contains(@placeholder,'Пароль')]");

    // Повтор пароля
    private final SelenideElement repeatPasswordInput = $x("//input[contains(@placeholder,'Повторите пароль')]");

    // Кнопка регистрации
    private final SelenideElement registerAccountButton = $x("//button[contains(text(), 'Создать аккаунт')]");


    public void register(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        repeatPasswordInput.setValue(password);
        registerAccountButton.click();
    }
}
