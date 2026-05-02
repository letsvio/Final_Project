package ru.yandex.praktikum;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreateAdPage {

    private final SelenideElement titleInput =
            $x("//input[@name='name']");

    private final SelenideElement categoryDropdown =
            $x("//input[@name='category']/following-sibling::button");

    private final SelenideElement cityDropdown =
            $x("//input[@name='city']/following-sibling::button");

    private final SelenideElement descriptionInput =
            $x("//textarea[@name='description']");

    private final SelenideElement priceInput =
            $x("//input[@name='price']");

    private final SelenideElement conditionNew =
            $x("//input[@value='Новый']/following-sibling::div");

    private final SelenideElement publishButton =
            $x("//button[normalize-space()='Опубликовать']");


    public void fillForm(String title, String category, String city,
                         String description, String price) {

        titleInput.shouldBe(visible).setValue(title);

        Selenide.executeJavaScript("document.querySelector('.homePage_modal__zSdUB')?.remove();");

        categoryDropdown.shouldBe(visible).click();
        $x("//span[text()='" + category + "']").shouldBe(visible).click();

        cityDropdown.shouldBe(visible).click();
        $x("//span[text()='" + city + "']").shouldBe(visible).click();

        conditionNew.shouldBe(visible).click();

        descriptionInput.shouldBe(visible).setValue(description);
        priceInput.shouldBe(visible).setValue(price);
    }

    public void clickPublish() {
        publishButton.click();
    }



}
