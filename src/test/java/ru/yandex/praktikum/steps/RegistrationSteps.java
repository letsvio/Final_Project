package ru.yandex.praktikum.steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.restassured.response.Response;
import ru.yandex.praktikum.LoginPage;
import ru.yandex.praktikum.BasePage;
import ru.yandex.praktikum.RegisterPage;
import ru.yandex.praktikum.api.UserRegisterApi;
import ru.yandex.praktikum.utils.FakerData;
import ru.yandex.praktikum.utils.TestContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationSteps {

    private final RegisterPage registerPage;
    private final LoginPage loginPage;
    private final UserRegisterApi registerApi;
    private final TestContext context;


    public RegistrationSteps(RegisterPage registerPage,
                             LoginPage loginPage,
                             UserRegisterApi registerApi,
                             TestContext context) {
        this.registerPage = registerPage;
        this.loginPage = loginPage;
        this.registerApi = registerApi;
        this.context = context;
    }

    @И("переходит на форму регистрации")
    public void goToRegister() {
        loginPage.clickNoAccountButton();
    }

    @И("заполняет форму регистрации")
    public void fillRegistrationForm() {
        registerPage.register(context.getEmail(), context.getPassword());
    }

    @И("пытается зарегистрироваться повторно с тем же email")
    public void tryRegisterAgain() {
        registerPage.register(context.getEmail(), context.getPassword());
    }

    @Тогда("отображается ошибка о существующем пользователе")
    public void checkUserAlreadyExistsError() {
        // Проверка через API
        Response response = registerApi.register(context.getEmail(), context.getPassword());
        response.then().statusCode(400);

        String message = response.jsonPath().getString("message");
        assertThat("Неверное сообщение об ошибке",
                message, equalTo("Почта уже используется"));
    }

    @Тогда("пользователь успешно зарегистрирован")
    public void checkSuccessfulRegistration() {
        BasePage basePage = new BasePage();
        basePage.shouldNotSeeAuthButton();
    }

    @И("заполняет форму регистрации новыми уникальными данными")
    public void fillRegistrationFormWithNewData() {
        String email = FakerData.email();
        String password = FakerData.password();


        registerPage.register(email, password);

        System.out.println("✅ Заполнена форма регистрации новыми данными: " + email);
    }
}