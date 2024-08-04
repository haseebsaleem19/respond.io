package general;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * This class provides common assertion methods for use in test cases.
 * It utilizes TestNG's assertion capabilities to verify test outcomes.
 */
public class CommonAssertions {
    private final WebDriver driver;

    /**
     * Constructor to initialize the WebDriver instance.
     *
     * @param driver the WebDriver instance used for browser interactions.
     */
    public CommonAssertions(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Asserts that two integer values are equal.
     *
     * @param actual the actual value obtained during the test.
     * @param expected the expected value to compare against.
     */
    public static void assertion(int actual, int expected) {
        Assert.assertEquals(actual, expected);
    }
}