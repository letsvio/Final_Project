package ru.yandex.praktikum.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import ru.yandex.praktikum.LoginPage;
import ru.yandex.praktikum.SuccessfulLoginPage;
import ru.yandex.praktikum.utils.TestContext;


public class AuthorizationSteps {

    private final LoginPage loginPage;
    private final SuccessfulLoginPage successfulLoginPage;
    private final TestContext context;

    public AuthorizationSteps(LoginPage loginPage, SuccessfulLoginPage successfulLoginPage, TestContext context) {
        this.loginPage = loginPage;
        this.successfulLoginPage = successfulLoginPage;
        this.context = context;
    }

    @И("авторизуется с валидными данными")
    public void авторизуетсяСВалиднымиДанными() {
        loginPage.login(context.getEmail(), context.getPassword());
    }

    @Тогда("пользователь успешно авторизован и видит кнопку \"Разместить объявление\"")
    public void checkSuccessfulLogin() {
        successfulLoginPage.shouldSeeCreateAdButton();
    }
}