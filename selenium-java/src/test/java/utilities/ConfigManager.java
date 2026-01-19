package utilities;

import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(ConfigManager.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    /**
     * Get a configuration property with precedence:
     * System property > Environment variable > config.properties
     *
     * @param key the property key
     * @return the property value, or null if not found
     */
    public static String getProperty(String key) {
        String value = System.getProperty(key);
        if (value != null) {
            return value;
        }

        value = System.getenv(key);
        if (value != null) {
            return value;
        }

        return properties.getProperty(key);
    }
}
