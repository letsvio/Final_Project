package ru.yandex.praktikum;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class EditAdPage {
    private final SelenideElement titleInput = $x("//input[@name='name']");
    private final SelenideElement descriptionInput = $x("//textarea[@name='description']");
    private final SelenideElement saveButton = $x("//button[contains(text(),'Сохранить')]");

    public void updateTitleAndDescription(String newTitle, String newDescription) {
        titleInput.clear();
        titleInput.setValue(newTitle);
        descriptionInput.clear();
        descriptionInput.setValue(newDescription);
    }

    public void saveChanges() {
        saveButton.click();
    }
}