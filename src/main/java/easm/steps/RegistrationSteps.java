package easm.steps;

import easm.dto.RegistrationDTO;
import easm.pages.RegistrationPage;
import io.qameta.allure.Step;

public class RegistrationSteps {


    @Step("Регистрация нового пользователя")
    public static RegistrationPage registr(RegistrationDTO dto) {
        return RegistrationPage.open()
                .fillUserName(dto.getUserName())
                .fillEmail(dto.getLogin())
                .fillPhoneNumber(dto.getPhoneNumber())
                .fillPassword(dto.getPassword())
                .fillPasswordValidation(dto.getPassword())
                .clickSingUpForm()
                .clickSubmit()
                .verifyAndAcceptBrowserPopup("Сейчас на ваш телефон поступит звонок или сообщение, последние 4 цифры являются кодом");
    }

    @Step("Регистрация нового пользователя с ошибкой")
    public static RegistrationPage registrWithError(RegistrationDTO dto, String errorText) {
        return RegistrationPage.open()
                .fillUserName(dto.getUserName())
                .fillEmail(dto.getLogin())
                .fillPhoneNumber(dto.getPhoneNumber())
                .fillPassword(dto.getPassword())
                .fillPasswordValidation(dto.getPassword())
                .clickSingUpForm()
                .clickSubmit()
                .verifyErrorWithOutElement(errorText);
    }
}
