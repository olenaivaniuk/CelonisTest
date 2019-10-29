package com.b2;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

//import org.junit.jupiter.api.Test;

@RunWith(JUnit4.class)
public class WebDriverTest {

    private static int TIMEOUT = 5; // in seconds
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\oivaniuk\\Downloads\\B2Tests\\webdriver\\chromedriver.exe");
    }

    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS); // depends on latency defined in SLA/SLO ???

        wait = new WebDriverWait(driver, TIMEOUT);
    }

    @After
    public void afterTest() {
        driver.close();
    }

    @Test
    public void test() {
        driver.get("https://applications.eu-1.celonis.cloud/ui/login");
        wait.until(ExpectedConditions.textToBePresentInElement(
                driver.findElement(By.tagName("body")), "Sign in - Celonis"));

        /*WebElement loginAccess = driver.findElement(By.id("login-access-btn"));
        loginAccess.click();

        WebElement emailField = driver.findElement(By.id("be2_login_username"));
        emailField.clear();
        emailField.sendKeys("testcandidate10@testsystem.fc4cd.com");

        WebElement passwordField = driver.findElement(By.id("be2_login_password"));
        passwordField.clear();
        passwordField.sendKeys("shGBGe3gAG");

        WebElement loginButton = driver.findElement(By.id("be2_loginButton"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("See all messages")));

        WebElement allMessages = driver.findElement(By.linkText("See all messages"));
        allMessages.click();

        List<WebElement> myMessages = driver.findElements(By.className("contact"));
        myMessages.get(myMessages.size() - 1).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("region")));
        WebElement customerServiceMessage = driver.findElement(By.xpath("//*[@id=\"messaging\"]/section[2]/section/a/section/div[2]"));
        assertEquals("Customer Service", customerServiceMessage.getText());


        WebElement myProfile = driver.findElement(By.className("my-profile-text"));
        myProfile.click();

        WebElement myAge = driver.findElement(By.xpath("//*[@id=\"content-contents-wrapper\"]/myprofile-view/div/div/section[2]/div[2]/section/table/tbody/tr[2]/td[2]"));
        assertEquals("35", myAge.getText());

        WebElement logoutButton = driver.findElement(By.xpath("//*[@id=\"header-elements-layout1\"]/div/div[2]/ul/li[8]/a"));
        logoutButton.click();*/
    }

}
