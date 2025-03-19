package easm.provider;

import com.github.javafaker.Faker;
import easm.dto.RegistrationDTO;

import java.util.Random;

public class RegistrationProvider {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static RegistrationDTO getRegistrationForm() {
        String password = getPassword();
        return RegistrationDTO.builder()
                .userName(getFullName())
                .login(faker.internet().emailAddress())
                .phoneNumber(generatePhoneNumber())
                .password(password)
                .passwordValidation(password)
                .build();
    }

    public static RegistrationDTO getRegistrationFormWithWrongName() {
        System.out.println(generatePhoneNumber());
        return RegistrationDTO.builder()
                .userName(String.valueOf(faker.number().numberBetween(1,1)))
                .login(faker.internet().emailAddress())
                .phoneNumber(generatePhoneNumber())
                .password(getPassword())
                .passwordValidation(getPassword())
                .build();
    }

    public static RegistrationDTO getRegistrationFormWithWrongPassword() {
        return RegistrationDTO.builder()
                .userName(getFullName())
                .login(faker.internet().emailAddress())
                .phoneNumber(generatePhoneNumber())
                .password(String.valueOf(faker.number().numberBetween(1,1)))
                .passwordValidation(String.valueOf(faker.number().numberBetween(1,1)))
                .build();
    }

    private static String getPassword() {
        return faker.internet().password(8, 16, true, true);
    }

    private static String generatePhoneNumber() {
        int firstDigit = random.nextBoolean() ? 79 : 89;
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append(firstDigit);

        for (int i = 0; i < 9; i++) {
            phoneNumber.append(random.nextInt(9));
        }
        return phoneNumber.toString();
    }

    private static String getFullName() {
        return faker.name().fullName().replaceAll("[\\s\\.\\-']+", "");
    }
}
