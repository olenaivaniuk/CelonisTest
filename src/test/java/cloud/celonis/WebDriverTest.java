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

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class WebDriverTest {

    private static int TIMEOUT = 5; // in seconds

    private static String ORDER_TO_CASH_ID_VALUE =
            "analysisListComponent-analysisName-Order to Cash - Process Overview (EUR) - EN-button";
    private static String PURCHASE_TO_PAY_ID_VALUE =
            "analysisListComponent-analysisName-Purchase to Pay - Demo - EN (EUR)-button";
    private static String SERVICE_NOW_TICKETING_ID_VALUE =
            "analysisListComponent-analysisName-ServiceNow - Ticket Status-button";
    private static String VARIANT_EXPLORER_COMPONENT_ID_VALUE = "variantExplorer-component-variantExplorerComponent";

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Elena\\Desktop\\CelonisTest\\seleniumdriver\\chromedriver.exe");
    }

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS); // depends on latency defined in SLA/SLO ???

        wait = new WebDriverWait(driver, TIMEOUT);

        driver.get("https://applications.eu-1.celonis.cloud/");
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
        assertEquals(1, driver.findElements(
                By.xpath("//a[@data-testing-uid='" + ORDER_TO_CASH_ID_VALUE + "']")).size());

        assertEquals(1, driver.findElements(
                By.xpath("//a[@data-testing-uid='" + PURCHASE_TO_PAY_ID_VALUE + "']")).size());

        assertEquals(1, driver.findElements(
                By.xpath("//a[@data-testing-uid='" + SERVICE_NOW_TICKETING_ID_VALUE + "']")).size());
    }

    @Test
    public void testOrderToCash() {
        WebElement orderToCashLink =
                driver.findElement(By.xpath("//a[@data-testing-uid='" + ORDER_TO_CASH_ID_VALUE + "']"));
        orderToCashLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-testing-uid='" + VARIANT_EXPLORER_COMPONENT_ID_VALUE + "']")));
    }

    @Test
    public void testPurchaseToPay() {
        WebElement purchaseToPayLink = driver.findElement(
                By.xpath("//a[@data-testing-uid='" + PURCHASE_TO_PAY_ID_VALUE + "']"));
        purchaseToPayLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-testing-uid='" + VARIANT_EXPLORER_COMPONENT_ID_VALUE + "']")));
    }

    @Test
    public void testServiceNowTicketing() {
        WebElement serviceNowTicketingLink = driver.findElement(
                By.xpath("//a[@data-testing-uid='" + SERVICE_NOW_TICKETING_ID_VALUE + "']"));
        serviceNowTicketingLink.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-testing-uid='" + VARIANT_EXPLORER_COMPONENT_ID_VALUE + "']")));
    }
}
