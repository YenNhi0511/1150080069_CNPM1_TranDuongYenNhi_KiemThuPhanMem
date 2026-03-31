package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigReader - Doc config.properties theo moi truong (dev/staging/prod).
 * Su dung Singleton pattern de dam bao chi doc file 1 lan.
 */
public class ConfigReader {

    private static ConfigReader instance;
    private final Properties properties;

    private ConfigReader() {
        properties = new Properties();
        String env = System.getProperty("env", "dev");
        String fileName = "config-" + env + ".properties";

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.out.println("Khong tim thay file: " + fileName + ", thu doc config-dev.properties");
                try (InputStream fallback = getClass().getClassLoader()
                        .getResourceAsStream("config-dev.properties")) {
                    if (fallback != null) {
                        properties.load(fallback);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Khong the doc file config: " + fileName, e);
        }

        // Override bang environment variables (cho CI/CD)
        overrideWithEnvVars();
    }

    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    /**
     * Reset instance - dung khi can reload config (vi du doi env).
     */
    public static void reset() {
        instance = null;
    }

    private void overrideWithEnvVars() {
        String envUsername = System.getenv("SAUCEDEMO_USERNAME");
        String envPassword = System.getenv("SAUCEDEMO_PASSWORD");

        if (envUsername != null && !envUsername.isEmpty()) {
            properties.setProperty("username", envUsername);
        }
        if (envPassword != null && !envPassword.isEmpty()) {
            properties.setProperty("password", envPassword);
        }
    }

    public String getBaseUrl() {
        return properties.getProperty("base.url", "https://www.saucedemo.com/");
    }

    public String getUsername() {
        return properties.getProperty("username", "standard_user");
    }

    public String getPassword() {
        return properties.getProperty("password", "secret_sauce");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
