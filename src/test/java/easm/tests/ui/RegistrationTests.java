package easm.tests.ui;

import easm.config.UiConfiguration;
import easm.dto.RegistrationDTO;
import easm.provider.RegistrationProvider;
import easm.steps.RegistrationSteps;
import io.qameta.allure.*;
import lombok.SneakyThrows;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Registration Module")
@Feature("UI Tests")
public class RegistrationTests {

    @BeforeMethod
    public void setUp() {
        UiConfiguration.setupSelenide("C:\\Users\\Sapolsky\\Documents\\easm\\src\\main\\resources\\chromedriver.exe");
    }

    @Test
    @Story("Positive Scenarios")
    @Description("Verify successful registration with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @SneakyThrows
    public void testSuccessfulRegistration() {
        RegistrationDTO registrationDTO = RegistrationProvider.getRegistrationForm();
        RegistrationSteps.registr(registrationDTO);
    }

    @Test
    @Story("Negative Scenarios")
    @Description("Verify error on invalid name format")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidName() {
        RegistrationDTO registrationDTO = RegistrationProvider.getRegistrationFormWithWrongName();
        RegistrationSteps.registrWithError(registrationDTO, "Имя может содержать только буквы английского и русского алфавита");
    }

    @Test
    @Story("Negative Scenarios")
    @Description("Verify error on invalid password format")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidPassword() {
        RegistrationDTO registrationDTO = RegistrationProvider.getRegistrationFormWithWrongPassword();
        RegistrationSteps.registrWithError(registrationDTO, "Пароль должен быть не менее 8 символов");
    }
}