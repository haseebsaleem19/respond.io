package config;

import java.io.InputStream;
import java.util.Properties;

/**
 * This class reads configuration properties from the ApplicationConfig.properties file.
 */
public class ApplicationConfigReader {
    // Properties object to store configuration properties
    private static final Properties properties = new Properties();

    static {
        // Load configuration properties when the class is initialized
        try (InputStream input = ApplicationConfigReader.class.getClassLoader().getResourceAsStream("ApplicationConfig.properties")) {
            properties.load(input);
        } catch (Exception e) {
            // Handle any exception that might occur during the loading of properties
            e.printStackTrace();
        }
    }

    /**
     * Get the protocol from the configuration properties.
     * @return The protocol value.
     */
    public static String getProtocol() {
        return properties.getProperty("protocol");
    }

    /**
     * Get the base URL from the configuration properties.
     * @return The base URL value.
     */
    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    /**
     * Get the path from the configuration properties.
     * @return The path value.
     */
    public static String getPath() {
        return properties.getProperty("path");
    }

    /**
     * Get the email from the configuration properties.
     * @return The email value.
     */
    public static String getEmail() {
        return properties.getProperty("email");
    }

    /**
     * Get the password from the configuration properties.
     * @return The password value.
     */
    public static String getPassword() {
        return properties.getProperty("password");
    }

    /**
     * Get the content type from the configuration properties.
     * @return The content type value.
     */
    public static String getContentType() {
        return properties.getProperty("content_type");
    }

    /**
     * Get the API path from the configuration properties.
     * @return The API path value.
     */
    public static String getApiPath() {
        return properties.getProperty("apiPath");
    }

    /**
     * Get the organization ID from the configuration properties.
     * @return The organization ID value.
     */
    public static String getOrgId() {
        return properties.getProperty("orgId");
    }

    /**
     * Get the workspace creation endpoint from the configuration properties.
     * @return The workspace creation endpoint value.
     */
    public static String getCreateWorkSpace() {
        return properties.getProperty("createWorkSpace");
    }

    /**
     * Get the token from the configuration properties.
     * @return The token value.
     */
    public static String getToken() {
        return properties.getProperty("token");
    }

    /**
     * Get the name from the configuration properties.
     * @return The name value.
     */
    public static String getName() {
        return properties.getProperty("name");
    }

    /**
     * Get the timezone from the configuration properties.
     * @return The timezone value.
     */
    public static String getTimezone() {
        return properties.getProperty("timezone");
    }
}