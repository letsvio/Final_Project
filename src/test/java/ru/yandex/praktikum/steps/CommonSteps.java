package ru.yandex.praktikum.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import ru.yandex.praktikum.BasePage;
import ru.yandex.praktikum.LoginPage;
import ru.yandex.praktikum.SuccessfulLoginPage;
import ru.yandex.praktikum.api.UserAuthApi;
import ru.yandex.praktikum.api.UserRegisterApi;
import ru.yandex.praktikum.utils.FakerData;
import ru.yandex.praktikum.utils.FinalData;
import ru.yandex.praktikum.utils.TestContext;
import ru.yandex.praktikum.utils.UserCache;


public class CommonSteps {

    private final TestContext context;
    private final BasePage basePage;
    private final UserRegisterApi registerApi;
    private final UserAuthApi authApi;

    public CommonSteps(TestContext context, BasePage basePage, UserRegisterApi registerApi, UserAuthApi authApi) {
        this.context = context;
        this.basePage = basePage;
        this.registerApi = registerApi;
        this.authApi = authApi;
    }

    @Дано("пользователь с уникальным email и паролем создан через API")
    public void createUserViaApi() {
        if (UserCache.email == null) {
            String email = FakerData.email();
            String password = FakerData.password();

            registerApi.register(email, password).then().statusCode(201);

            UserCache.email = email;
            UserCache.password = password;
        }

        context.setEmail(UserCache.email);
        context.setPassword(UserCache.password);
    }

    @Дано("пользователь успешно авторизован")
    public void loginUser() {

        Selenide.open(FinalData.BASE_URL);
        basePage.clickAuthButton();

        LoginPage loginPage = new LoginPage();
        loginPage.login(context.getEmail(), context.getPassword());


        String token = authApi.authAndGetToken(context.getEmail(), context.getPassword());
        context.setToken(token);

        SuccessfulLoginPage page = new SuccessfulLoginPage();
        page.shouldSeeCreateAdButton();
    }


    @Дано("пользователь зарегистрирован через API")
    public void registerUser() {
        createUserViaApi();
    }

    @Дано("пользователь зарегистрирован и авторизован")
    public void registerAndAuthUser() {
        createUserViaApi();
    }

    @Дано("пользователь зарегистрирован и авторизован через API")
    public void registerAndLoginViaApi() {
        createUserViaApi();
    }

    @Когда("пользователь открывает главную страницу")
    public void openMainPage() {
        Selenide.open(ru.yandex.praktikum.utils.FinalData.BASE_URL);
    }

    @Когда("нажимает кнопку \"Вход и регистрация\"")
    public void clickAuthButton() {
        basePage.clickAuthButton();
    }
}