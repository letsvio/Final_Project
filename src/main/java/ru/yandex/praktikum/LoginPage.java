package ru.yandex.praktikum;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {


    private final SelenideElement noAccountButton = $x("//button[contains(text(),'Нет аккаунта')]");
    private final SelenideElement emailInput = $x("//input[contains(@placeholder,'Email') or @name='email']");
    private final SelenideElement passwordInput = $x("//input[contains(@placeholder,'Пароль')]");
    private final SelenideElement loginButton = $x("//button[contains(text(),'Войти')]");

    public void clickNoAccountButton() {
        noAccountButton.click();
    }



    public void login(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        loginButton.click();
    }
}
