package ru.yandex.praktikum.steps;

import io.cucumber.java.ru.*;
import io.restassured.response.Response;
import ru.yandex.praktikum.*;
import ru.yandex.praktikum.api.*;
import ru.yandex.praktikum.utils.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AdSteps {

    private final CreateAdPage createAdPage;
    private final SuccessfulLoginPage successfulLoginPage;
    private final EditAdPage editAdPage;
    private final AdDetailsPage adDetailsPage;

    private final UserAuthApi authApi;
    private final UserCreateAdApi createAdApi;

    private final TestContext context;

    public AdSteps(CreateAdPage createAdPage,
                   SuccessfulLoginPage successfulLoginPage,
                   EditAdPage editAdPage,
                   AdDetailsPage adDetailsPage,
                   UserAuthApi authApi,
                   UserCreateAdApi createAdApi,
                   TestContext context) {

        this.createAdPage = createAdPage;
        this.successfulLoginPage = successfulLoginPage;
        this.editAdPage = editAdPage;
        this.adDetailsPage = adDetailsPage;
        this.authApi = authApi;
        this.createAdApi = createAdApi;
        this.context = context;
    }


    @Когда("переходит в форму создания объявления")
    public void goToCreateAd() {
        successfulLoginPage.clickCreateAdButton();
    }

    @И("заполняет форму объявления валидными данными")
    public void fillAdForm() {
        String title = generateTitle();
        context.setLastAdTitle(title);

        createAdPage.fillForm(
                title,
                CategoryData.Книги.name(),
                CityData.MOSCOW.getValue(),
                "Описание",
                "7500"
        );
    }

    @И("публикует объявление")
    public void publishAd() {
        createAdPage.clickPublish();
    }

    @Тогда("объявление успешно опубликовано")
    public void checkAdPublished() {
        assertAdVisible();
    }

    @Дано("у пользователя есть опубликованное объявление")
    public void ensureUserHasPublishedAd() {
        if (context.getAdId() != null || context.getLastAdTitle() != null) {
            return;
        }

        String token = getValidToken();

        String title = generateTitle();
        context.setLastAdTitle(title);

        Response response = createAdApi.createAd(
                token,
                title,
                CategoryData.Книги.name(),
                "Новый",
                CityData.MOSCOW.getValue(),
                "Описание",
                "10000"
        );

        context.setAdId(createAdApi.getAdId(response));
    }

    @Когда("пользователь открывает страницу редактирования объявления")
    public void openEditPage() {
        openAdFromList();
        adDetailsPage.clickEdit();
    }

    @Когда("изменяет данные объявления")
    public void editAd() {
        String newTitle = generateTitle();
        context.setLastAdTitle(newTitle);

        editAdPage.updateTitleAndDescription(newTitle, "Новое описание");
    }

    @Когда("сохраняет изменения")
    public void saveChanges() {
        editAdPage.saveChanges();
    }

    @Тогда("изменения успешно применены")
    public void checkChanges() {
        assertAdVisible();
    }

    @Когда("пользователь удаляет объявление")
    public void deleteAd() {
        openAdFromList();
        adDetailsPage.clickDelete();
    }

    @Тогда("объявление успешно удалено")
    public void checkAdDeleted() {
        assertAdNotVisible();
    }


    private void openAdFromList() {
        successfulLoginPage.openAdByTitle(context.getLastAdTitle());
    }

    private String getValidToken() {
        String token = context.getToken();

        if (token == null || token.isEmpty()) {
            token = authApi.authAndGetToken(
                    context.getEmail(),
                    context.getPassword()
            );
            context.setToken(token);
        }

        return token;
    }

    private String generateTitle() {
        return "Тест " + System.currentTimeMillis();
    }

    private void assertAdVisible() {
        successfulLoginPage.searchAdByFilters(
                context.getLastAdTitle(),
                CategoryData.Книги.name(),
                CityData.MOSCOW.getValue()
        );
        $x("//h2[contains(@class,'h2') and contains(text(),'" + context.getLastAdTitle() + "')]")
                .shouldBe(visible.because("Объявление должно быть видно после поиска"));
    }

    private void assertAdNotVisible() {
        successfulLoginPage.searchAdByFilters(
                context.getLastAdTitle(),
                CategoryData.Книги.name(),
                CityData.MOSCOW.getValue()
        );
        $x("//h2[contains(@class,'h2') and contains(text(),'" + context.getLastAdTitle() + "')]")
                .shouldNotBe(visible)
                .shouldNotBe(exist);
    }
}