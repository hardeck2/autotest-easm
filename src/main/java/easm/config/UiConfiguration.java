package easm.config;

import com.codeborne.selenide.Configuration;

import java.io.IOException;
import java.util.Properties;
public class UiConfiguration {
    private static final Properties properties = loadProperties();

    private static Properties loadProperties() {
        Properties props = new Properties();
        try {
            props.load(UiConfiguration.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load application.properties", e);
        }
        return props;
    }

    public static void setupSelenide(String chromeDriverPath) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        Configuration.browser = properties.getProperty("browser", "chrome");
        Configuration.timeout = Long.parseLong(properties.getProperty("timeout", "10000"));
        Configuration.headless = Boolean.parseBoolean(properties.getProperty("headless", "false"));
        Configuration.baseUrl = properties.getProperty("base.url");
    }

    public static String getRegistrationPath() {
        return properties.getProperty("base.url");
    }
}
