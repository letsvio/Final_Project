package ru.yandex.praktikum;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class AdDetailsPage {
    private final SelenideElement editButton = $x("//button[contains(text(),'Редактировать')]");
    private final SelenideElement deleteButton = $x("//button[contains(text(),'Удалить')]");

    public void clickEdit() {
        editButton.shouldBe(Condition.visible).click();
    }

    public void clickDelete() {
        deleteButton.shouldBe(Condition.visible).click();
    }
}