package easm.config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import java.io.IOException;
import java.util.Properties;

public class ApiConfiguration {

    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try {
            props.load(ApiConfiguration.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
        return props;
    }

    public static void setupRestAssured() {
        RestAssured.baseURI = properties.getProperty("api.base.url");
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(properties.getProperty("api.content.type", "application/json"))
                .build();
    }
}
