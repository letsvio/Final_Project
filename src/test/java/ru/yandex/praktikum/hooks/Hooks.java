package ru.yandex.praktikum.hooks;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import ru.yandex.praktikum.utils.TestContext;

import static ru.yandex.praktikum.utils.FinalData.BASE_URL;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void beforeEach(Scenario scenario) {
        System.out.println("→ Запуск сценария: " + scenario.getName());

        if (scenario.getName().contains("создание объявления")) {
            context.clear();
            System.out.println("Контекст очищен для начала новой фичи");
        }

        Selenide.open(BASE_URL);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @After
    public void afterEach(Scenario scenario) {
        System.out.println("→ Завершение сценария: " + scenario.getName() +
                " | Статус: " + scenario.getStatus());

        if (scenario.isFailed()) {
            takeScreenshot(scenario.getName());
        }

        if (!scenario.getSourceTagNames().contains("@no-close-browser")) {
            try {
                Selenide.closeWebDriver();
                System.out.println("Браузер успешно закрыт");
            } catch (Exception e) {
                System.out.println("Ошибка при закрытии браузера: " + e.getMessage());
            }
        } else {
            System.out.println("Браузер НЕ закрыт (тег @no-close-browser)");
        }
    }

    @Attachment(value = "Скриншот ошибки", type = "image/png")
    public byte[] takeScreenshot(String scenarioName) {
        try {
            return Selenide.screenshot(OutputType.BYTES);
        } catch (Exception e) {
            return new byte[0];
        }
    }
}