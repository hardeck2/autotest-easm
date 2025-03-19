package api;

import easm.config.ApiConfiguration;
import easm.dto.RegistrationDTO;
import easm.dto.RegistrationResponse;
import easm.provider.RegistrationProvider;
import easm.webClient.RegistrationApiClient;
import io.qameta.allure.*;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("Registration Module")
@Feature("API Tests")
public class RegistrationTests {

    private static final RegistrationApiClient apiClient = new RegistrationApiClient();

    @BeforeMethod
    public void setUp() {
        ApiConfiguration.setupRestAssured();
    }

    @Test
    @Story("Positive Scenarios")
    @Description("Verify successful registration API")
    @Severity(SeverityLevel.CRITICAL)
    public void testSuccessfulRegistration() {
        RegistrationDTO request = RegistrationProvider.getRegistrationForm();
        RegistrationResponse register = apiClient.registerAndGetResponse(request);
        Assertions.assertThat(register.isType()).isTrue();
        Assertions.assertThat(register.getText()).contains("Сейчас на ваш телефон поступит звонок или сообщение, последние 4 цифры являются кодом");
    }

    @Test
    @Story("Negative Scenarios")
    @Description("Verify unsuccessful registration API with wrong userName")
    @Severity(SeverityLevel.CRITICAL)
    public void testUnSuccessfulRegistration() {
        RegistrationDTO request = RegistrationProvider.getRegistrationFormWithWrongName();
        RegistrationResponse register = apiClient.registerAndGetResponse(request);

        Assertions.assertThat(register.isType()).isFalse();
        Assertions.assertThat(register.getMessage()).contains("Ваше имя (ФИО) не должно быть короче 3 символов");
    }


    @Test
    @Story("Negative Scenarios")
    @Description("Verify error on empty fields")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyFields() {
        RegistrationDTO request = RegistrationProvider.getRegistrationFormWithWrongPassword();
        RegistrationResponse register = apiClient.registerAndGetResponse(request);

        Assertions.assertThat(register.isType()).isFalse();
        Assertions.assertThat(register.getMessage()).contains("Пароль должен состоять минимум из 8 символов");
    }
}
