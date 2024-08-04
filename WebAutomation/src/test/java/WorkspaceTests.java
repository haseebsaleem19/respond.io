import config.ApplicationConfigReader;
import general.BaseTest;
import objects.WorkspacePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import objects.LoginPage;

/**
 * Tests related to workspace functionalities in the application.
 */
public class WorkspaceTests extends BaseTest {
    private WorkspacePage workspacePage;
    private LoginPage loginPage;

    /**
     * Initializes the necessary page objects before each test method.
     */
    @BeforeMethod
    public void setUp() {
        // Initialize the LoginPage and WorkspacePage objects
        loginPage = new LoginPage(driver);
        workspacePage = new WorkspacePage(driver);
    }

    /**
     * Tests the workflow for adding a new workspace.
     * This test performs a valid login, verifies that the workspace page is loaded correctly,
     * adds a new workspace, and verifies that the 'Invite Users to Workspace' span is displayed.
     *
     * @throws InterruptedException if the thread is interrupted during execution
     */
    @Test
    public void addWorkspace() throws InterruptedException {
        // Perform valid login
        loginPage.login(ApplicationConfigReader.getUsername(), ApplicationConfigReader.getPassword());

        // Verify that an image element with the specified classes is present
        Assert.assertTrue(driver.findElements(By.xpath("//img[contains(@class, 'v-img__img') and contains(@class, 'v-img__img--contain')]")).size() > 0, "The img element with the specified classes does not exist.");

        // Navigate to the workspace page and add a new workspace
        workspacePage.clickOnWorkspace();
        workspacePage.insertWorkspace();
        workspacePage.clickOnNextButton();

        // Verify that the 'Invite Users to Workspace' span is displayed
        WebElement spanElement = driver.findElement(By.xpath("//span[@class='text-h6' and text()='Invite Users to Workspace']"));
        Assert.assertTrue(spanElement.isDisplayed(), "The 'Invite Users to Workspace' span is not displayed.");
    }
}