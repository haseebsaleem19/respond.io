package config;

import java.io.InputStream;
import java.util.Properties;

/**
 * The ApplicationConfigReader class is responsible for reading configuration
 * values from the ApplicationConfig.properties file. The class loads the
 * properties once and provides static methods to retrieve individual
 * configuration values.
 */
public class ApplicationConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ApplicationConfigReader.class.getClassLoader().getResourceAsStream("ApplicationConfig.properties")) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the URL from the configuration properties.
     *
     * @return The URL as a String.
     */
    public static String getUrl() {
        return properties.getProperty("url");
    }

    /**
     * Retrieves the username from the configuration properties.
     *
     * @return The username as a String.
     */
    public static String getUsername() {
        return properties.getProperty("username");
    }

    /**
     * Retrieves the password from the configuration properties.
     *
     * @return The password as a String.
     */
    public static String getPassword() {
        return properties.getProperty("password");
    }

    /**
     * Retrieves the browser type from the configuration properties.
     *
     * @return The browser type as a String.
     */
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
}