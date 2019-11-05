package cloud.celonis;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class WebDriverTest {

    private static int TIMEOUT = 5; // in seconds

    private static String ELEMENT_1_ID_VALUE =
            "analysisListComponent-analysisName-Order to Cash - Process Overview (EUR) - EN-button";
    private static String ELEMENT_2_ID_VALUE =
            "analysisListComponent-analysisName-Purchase to Pay - Demo - EN (EUR)-button";
    private static String ELEMENT_3_ID_VALUE =
            "analysisListComponent-analysisName-ServiceNow - Ticket Status-button";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Desktop\\CelonisTest\\seleniumdriver\\chromedriver.exe");
    }

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS); // depends on latency defined in SLA/SLO ???

        wait = new WebDriverWait(driver, TIMEOUT);

        driver.get("https://applications.eu-1.celonis.cloud/ui/login");
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.tagName("body")), "Sign in - Celonis"));

        WebElement emailField = driver.findElement(By.id("ce-input-0"));
        emailField.clear();
        emailField.sendKeys("ivaniuk.olenaqa@gmail.com");

        WebElement passwordField = driver.findElement(By.id("ce-input-1"));
        passwordField.clear();
        passwordField.sendKeys("BerlinMunchen911!Yes");

        WebElement signIn = driver.findElement(By.xpath("//button[@data-testing-uid='login-form-submit-button']"));
        signIn.click();

        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.tagName("h2")), "Workspaces"));
    }

    @After
    public void afterTest() {
        driver.close();
    }

    @Test
    public void testWorkspacesPresence() {
        assertTrue(
                driver.findElements(
                        By.xpath("//a[@data-testing-uid='" + ELEMENT_1_ID_VALUE + "']")).size() == 1);

        assertTrue(
                driver.findElements(
                        By.xpath("//a[@data-testing-uid='" + ELEMENT_2_ID_VALUE + "']")).size() == 1);

        assertTrue(
                driver.findElements(
                        By.xpath("//a[@data-testing-uid='" + ELEMENT_3_ID_VALUE + "']")).size() == 1);
    }

    @Test
    public void testOrderToCash() {
        WebElement processOverviewLink = driver.findElement(By.xpath("//a[@data-testing-uid='" + ELEMENT_1_ID_VALUE + "']"));
        processOverviewLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testing-uid='variantExplorer-component-variantExplorerComponent']")));
    }

    @Test
    public void testPurchaseToPay() {
        WebElement demoEnLink = driver.findElement(By.xpath("//a[@data-testing-uid='" + ELEMENT_2_ID_VALUE + "']"));
        demoEnLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testing-uid='variantExplorer-component-variantExplorerComponent']")));
    }

    @Test
    public void testServiceNowTicketing() {
        WebElement ticketStatusLink = driver.findElement(By.xpath("//a[@data-testing-uid='" + ELEMENT_3_ID_VALUE + "']"));
        ticketStatusLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-testing-uid='variantExplorer-component-variantExplorerComponent']")));
    }
}
