package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Represents the Workspace Page in the web application.
 * Provides methods to interact with elements on the Workspace Page.
 */
public class WorkspacePage {
    private final WebDriver driver;

    /**
     * Constructor to initialize the WorkspacePage with a WebDriver instance.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public WorkspacePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks on the 'Workspaces' section and then clicks on the 'Add Workspace' button.
     *
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    public void clickOnWorkspace() throws InterruptedException {
        Thread.sleep(5000); // Wait for the page to load
        driver.findElement(By.xpath("//span[text()[contains(., 'Workspaces')]]")).click();
        driver.findElement(By.xpath("(//button//span[contains(text(), 'Add Workspace')])[2]")).click();
    }

    /**
     * Enters a workspace name into the 'Name your workspace' input field.
     *
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    public void insertWorkspace() throws InterruptedException {
        driver.findElement(By.xpath("//input[@placeholder='Name your workspace e.g. Customer Support']")).sendKeys("TestWorkspace");
    }

    /**
     * Clicks the 'Next' button on the page.
     *
     * @throws InterruptedException if the thread sleep is interrupted.
     */
    public void clickOnNextButton() throws InterruptedException {
        driver.findElement(By.xpath("//span[text() = 'Next']")).click();
    }
}