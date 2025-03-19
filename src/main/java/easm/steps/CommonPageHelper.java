package easm.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$x;

public class CommonPageHelper {

    private static final String xpathByText = "//*[text()='%s']";

    @Step("Заполнить поле значение {value}")
    public static void fillTextField(SelenideElement field, String value) {
        fillField(field, value);
    }


    @Step("Заполнить поле значение {value}")
    public static void fillField(SelenideElement field, String value) {
        checkFieldAppear(field);
        field.setValue(value);
    }

    @Step("Проверка отображения поля {fieldName}")
    public static SelenideElement checkFieldAppear(SelenideElement field) {
        return checkAppear(field, "Поле '%s' должно отображаться".formatted(field));
    }

    @Step("Проверка отображения элемента")
    private static SelenideElement checkAppear(SelenideElement element, String because) {
        return element.shouldBe(appear.because(because));
    }

    @Step("Клик по кнопке {buttonName}")
    public static void clickButton(SelenideElement button, String buttonName) {
        checkButtonAppear(button, buttonName).click();
    }

    @Step("Проверить отображение кнопки {buttonName}")
    public static SelenideElement checkButtonAppear(SelenideElement button, String buttonName) {
        return checkAppear(button, "Кнопка '%s' должна отображаться".formatted(buttonName));
    }

    public static SelenideElement getElementByText(String text) {
        return $x(String.format(xpathByText, text)).as(text).shouldBe(Condition.visible);
    }
}
