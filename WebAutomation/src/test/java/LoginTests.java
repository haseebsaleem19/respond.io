import config.ApplicationConfigReader;
import general.BaseTest;
import objects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test class for testing login functionality on the application.
 * This class extends BaseTest and contains test methods to validate login scenarios.
 */
public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    /**
     * Setup method to initialize the LoginPage object before each test.
     * This method is called before each test method to ensure the LoginPage is correctly initialized.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage object only once
        loginPage = new LoginPage(driver);
    }

    /**
     * Test method to perform a valid login and verify successful login.
     * This test uses valid credentials and asserts that a specific image element indicating
     * a successful login is present on the page.
     */
    @Test
    public void validLoginTest() {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());
        Assert.assertTrue(driver.findElements(By.xpath("//img[contains(@class, 'v-img__img') and contains(@class, 'v-img__img--contain')]")).size() > 0, "The img element with the specified classes does not exist.");
    }

    /**
     * Test method to perform an invalid login and verify that an error element is present.
     * This test uses invalid credentials and asserts that an alert element indicating
     * an error is present on the page.
     */
    @Test
    public void invalidLoginTest() {
        // Perform invalid login
        loginPage.login(ApplicationConfigReader.getUsername(), "WrongPassword");
        boolean isElementPresent = isElementPresent(driver, "alert");

        // Assert that the element is present
        Assert.assertTrue(isElementPresent, "Element with ID 'alert' is not present on the page.");
    }

    /**
     * Helper method to check if an element with a specific ID is present on the page.
     *
     * @param driver The WebDriver instance used to interact with the browser.
     * @param id The ID of the element to check for.
     * @return true if the element is present, false otherwise.
     */
    public static boolean isElementPresent(WebDriver driver, String id) {
        try {
            WebElement element = driver.findElement(By.id(id));
            return element != null;
        } catch (Exception e) {
            return false;
        }
    }
}