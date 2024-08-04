package general;

import config.ApplicationConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * WebDriverFactory class is responsible for creating and managing the WebDriver instances.
 * It supports creating WebDriver instances for Chrome and Firefox browsers based on the configuration.
 */
public class WebDriverFactory {
    static WebDriver driver;

    /**
     * Creates a new WebDriver instance based on the browser specified in the application configuration.
     *
     * @return WebDriver instance for the specified browser.
     * @throws IllegalArgumentException if an invalid browser is specified in the configuration.
     */
    public static WebDriver createWebDriver() {
        String browser = ApplicationConfigReader.getBrowser().toLowerCase();

        switch (browser) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            default:
                throw new IllegalArgumentException("Invalid browser specified in the configuration: " + browser);
        }
    }

    /**
     * Creates a new WebDriver instance for Chrome browser.
     * Sets the path to the ChromeDriver executable.
     *
     * @return WebDriver instance for Chrome browser.
     */
    private static WebDriver createChromeDriver() {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        return new ChromeDriver();
    }

    /**
     * Creates a new WebDriver instance for Firefox browser.
     * Sets the path to the GeckoDriver executable.
     *
     * @return WebDriver instance for Firefox browser.
     */
    private static WebDriver createFirefoxDriver() {
        // Set the path to your GeckoDriver executable
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        return new FirefoxDriver();
    }

    /**
     * Returns the existing WebDriver instance if it is initialized.
     *
     * @return The existing WebDriver instance.
     * @throws IllegalStateException if the WebDriver instance has not been initialized.
     */
    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        } else {
            throw new IllegalStateException("Driver has not been initialized");
        }
    }
}