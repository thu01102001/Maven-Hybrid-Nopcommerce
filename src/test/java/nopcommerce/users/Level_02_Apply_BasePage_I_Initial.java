package nopcommerce.users;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_Apply_BasePage_I_Initial {
    WebDriver driver;
    private String emailAddress;
    private String firstName = "Automation";
    private String lastName = "FC";
    private String password = "123456";
    private BasePage basePage;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        basePage = new BasePage();

        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        basePage.openPageUrl(driver, "http://localhost/");
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");
        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'FirstName-error']"), "First name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'LastName-error']"), "Last name is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'Email-error']"), "Email is required.");
        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'ConfirmPassword-error']"), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id = 'FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id = 'LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id = 'Email']", "123@456#%*");
        basePage.sendkeyToElement(driver, "//input[@id = 'Password']", password);
        basePage.sendkeyToElement(driver, "//input[@id = 'ConfirmPassword']", password);

        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'Email-error']"), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Register_Success() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id = 'FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id = 'LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id = 'Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id = 'Password']", password);
        basePage.sendkeyToElement(driver, "//input[@id = 'ConfirmPassword']", password);

        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[@class = 'result']"), "Your registration completed");

        basePage.clickToElement(driver, "//a[@class = 'ico-logout']");
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id = 'FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id = 'LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id = 'Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id = 'Password']", password);
        basePage.sendkeyToElement(driver, "//input[@id = 'ConfirmPassword']", password);

        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//div[contains(@class, 'message-error')]//li"), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id = 'FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id = 'LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id = 'Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id = 'Password']", "123");
        basePage.sendkeyToElement(driver, "//input[@id = 'ConfirmPassword']", "123");

        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@class = 'field-validation-error']"), "<p>must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        basePage.clickToElement(driver, "//a[@class = 'ico-register']");

        basePage.sendkeyToElement(driver, "//input[@id = 'FirstName']", firstName);
        basePage.sendkeyToElement(driver, "//input[@id = 'LastName']", lastName);
        basePage.sendkeyToElement(driver, "//input[@id = 'Email']", emailAddress);
        basePage.sendkeyToElement(driver, "//input[@id = 'Password']", password);
        basePage.sendkeyToElement(driver, "//input[@id = 'ConfirmPassword']", "654321");

        basePage.clickToElement(driver, "//button[@id = 'register-button']");

        Assert.assertEquals(basePage.getElementText(driver, "//span[@id = 'ConfirmPassword-error']"), "The password and confirmation password do not match.");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public int generateFakeNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }
}
