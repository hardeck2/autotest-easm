package easm.webClient;

import easm.dto.RegistrationDTO;
import easm.dto.RegistrationResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RegistrationApiClient {

    public Response register(RegistrationDTO request) {
        return given()
                .body(request)
                .when()
                .post("/calluserforsignup")
                .then()
                .extract().response();
    }

    public RegistrationResponse registerAndGetResponse(RegistrationDTO request) {
        return register(request).as(RegistrationResponse.class);
    }
}
