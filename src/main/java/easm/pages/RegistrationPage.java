package easm.pages;

import com.codeborne.selenide.As;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import easm.config.UiConfiguration;
import easm.steps.CommonPageHelper;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.switchTo;

@Getter
public class RegistrationPage {

    private static final String GET_BUTTON = "Получить";
    private static final String FIELD_USERNAME = "ФИО";
    private static final String FIELD_EMAIL = "Электронная почта";
    private static final String FIELD_PHONE_NUMBER = "Номер телефона";
    private static final String FIELD_PASSWORD = "Пароль";
    private static final String FIELD_PASSWORD_VALIDATION = "Повторите пароль";
    private static final String CHECKBOX_SIGN_UP_FORM = "Условия обработки ПД";
    private static final String SUBMIT_BUTTON = "Получить";
    private static final String ERROR_MESSAGE = "Ошибка";

    @As(GET_BUTTON)
    @FindBy(xpath = "//*[text()='Получить']")
    private SelenideElement getButton;

    @As(FIELD_USERNAME)
    @FindBy(xpath = "//*[@id='userName']")
    private SelenideElement userName;

    @As(FIELD_EMAIL)
    @FindBy(xpath = "//*[@id='email']")
    private SelenideElement email;

    @As(FIELD_PHONE_NUMBER)
    @FindBy(xpath = "//*[@id='phoneNumber']")
    private SelenideElement phoneNumber;

    @As(FIELD_PASSWORD)
    @FindBy(xpath = "//*[@id='password']")
    private SelenideElement password;

    @As(FIELD_PASSWORD_VALIDATION)
    @FindBy(xpath = "//*[@id='passwordValidation']")
    private SelenideElement passwordValidation;

    @As(CHECKBOX_SIGN_UP_FORM)
    @FindBy(xpath = "//*[contains(@class, 'signUpForm__checkoboxItem')][1]")
    private SelenideElement singUpForm;

    @As(SUBMIT_BUTTON)
    @FindBy(xpath = "//*[@id='submitLogin']")
    private SelenideElement submitLogin;

    @As(ERROR_MESSAGE)
    @FindBy(xpath = "//*[contains(@class, 'signUpForm__errText')]")
    private SelenideElement errorMessage;


    @Step("Открыть страницу регистрации")
    public static RegistrationPage open() {
        return Selenide.open(UiConfiguration.getRegistrationPath(), RegistrationPage.class);
    }

    @Step("Заполнить поле - " + FIELD_USERNAME)
    public RegistrationPage fillUserName(String value) {
        CommonPageHelper.fillTextField(userName, value);
        return this;
    }

    @Step("Заполнить поле - " + FIELD_EMAIL)
    public RegistrationPage fillEmail(String value) {
        CommonPageHelper.fillTextField(email, value);
        return this;
    }

    @Step("Заполнить поле - " + FIELD_PHONE_NUMBER)
    public RegistrationPage fillPhoneNumber(String value) {
        CommonPageHelper.fillTextField(phoneNumber, value);
        return this;
    }

    @Step("Заполнить поле - " + FIELD_PASSWORD)
    public RegistrationPage fillPassword(String value) {
        CommonPageHelper.fillTextField(password, value);
        return this;
    }

    @Step("Заполнить поле - " + FIELD_PASSWORD_VALIDATION)
    public RegistrationPage fillPasswordValidation(String value) {
        CommonPageHelper.fillTextField(passwordValidation, value);
        return this;
    }

    @Step("Нажать на чекбокс " + CHECKBOX_SIGN_UP_FORM)
    public RegistrationPage clickSingUpForm() {
        CommonPageHelper.clickButton(singUpForm, CHECKBOX_SIGN_UP_FORM);
        return this;
    }

    @Step("Нажать на кнопку " + SUBMIT_BUTTON)
    public RegistrationPage clickSubmit() {
        CommonPageHelper.clickButton(submitLogin, SUBMIT_BUTTON);
        return this;
    }

    public RegistrationPage verifyAndAcceptBrowserPopup(String expectedText) {
        var alert = switchTo().alert();
        String actualText = alert.getText();
        if (!actualText.equals(expectedText)) {
            throw new AssertionError(
                    String.format("Expected popup text '%s', but got '%s'", expectedText, actualText)
            );
        }
        alert.accept();
        return this;
    }

    public RegistrationPage verifyErrorWithOutElement(String text) {
        CommonPageHelper.getElementByText(text);
        return this;
    }


}
