package ru.yandex.praktikum;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class SuccessfulLoginPage {

    private final SelenideElement createAdButton = $x("//button[contains(text(),'Разместить объявление')]");

    private final SelenideElement inputTitle = $x("//input[@name='name' and contains(@placeholder, 'Я хочу купить')]");

    private final SelenideElement buttonSubmit = $x("//button[@type='submit' and contains(text(),'Применить')]");

    private final SelenideElement categoryDropdown =
            $x("//input[@name='category']/following-sibling::button");

    private final SelenideElement cityDropdown =
            $x("//input[@name='city']/following-sibling::button");

    private final SelenideElement profileButton =
            $x("//button[contains(@class, 'circleSmall')]");

    private final SelenideElement myProfileTitle =
            $x("//h1[contains(@class, 'h1') and contains(text(), 'Мой профиль')]");

    public void clickCategoryDropdown(String category) {
        categoryDropdown.shouldBe(visible).click();
        $x("//span[text()='" + category + "']").shouldBe(visible).click();
    }

    public void clickCityDropdown(String city) {
        cityDropdown.shouldBe(visible).click();
        $x("//span[text()='" + city + "']").shouldBe(visible).click();
    }

    public void clickCreateAdButton() {
        createAdButton.click();
    }

    public void clickButtonSubmit() {
        buttonSubmit.click();
    }

    public void clickProfileButton() {
        profileButton.shouldBe(visible).click();
    }

    public void shouldSeeMyProfilePage() {
        myProfileTitle.shouldBe(visible.because("Должен отображаться заголовок 'Мой профиль'"));
    }

    public void searchAdByFilters (String title, String category, String city) {
        SelenideElement searchInput = inputTitle;
        searchInput.shouldBe(visible).clear();
        searchInput.setValue(title);

        clickCategoryDropdown(category);

        clickCityDropdown(city);

        clickButtonSubmit();
    }
    public void shouldSeeCreateAdButton() {
        createAdButton.shouldBe(visible);
    }

    public void openAdByTitle(String title) {
        $x("//h2[text()='" + title + "']")
                .shouldBe(visible)
                .click();
    }
}
