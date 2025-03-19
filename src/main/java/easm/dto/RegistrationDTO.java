package easm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RegistrationDTO {

    private String userName;
    private String login;
    private String phoneNumber;
    private String password;
    private String passwordValidation;

}
