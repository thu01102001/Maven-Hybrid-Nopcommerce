package nopcommerce.users;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.CustomerInfoPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_04_PageObject extends BaseTest {
    WebDriver driver;
    private String emailAddress;
    private String firstName = "Automation";
    private String lastName = "FC";
    private String password = "123456";
    private BasePage basePage;
    private String appUrl;

    @Parameters({"appUrl", "browser"})
    @BeforeClass
    public void beforeClass(String appUrl, String browserName) {
        this.appUrl = appUrl;
        basePage = BasePage.getInstance();
        driver = getBrowserName(appUrl, browserName);

        emailAddress = "afc" + generateFakeNumber() + "@mail.vn";

        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_01_Register_Empty_Data() {
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageObject(driver);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getFirstNameErrorMsg(), "First name is required.");
        Assert.assertEquals(registerPage.getLastNameErrorMsg(), "Last name is required.");
        Assert.assertEquals(registerPage.getEmailErrorMsg(), "Email is required.");
        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "Password is required.");
    }

    @Test
    public void TC_02_Register_Invalid_Email() {
        registerPage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox("14818!@(943");
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailErrorMsg(), "Please enter a valid email address.");
    }

    @Test
    public void TC_03_Register_Success() {
        registerPage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSucessMsg(), "Your registration completed");

        registerPage.clickToLogoutLink();
        homePage = new HomePageObject(driver);
    }

    @Test
    public void TC_04_Register_Existing_Email() {
        homePage.clickToRegisterLink();
        registerPage = new RegisterPageObject(driver);

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getEmailAlreadyExistsMsg(), "The specified email already exists");
    }

    @Test
    public void TC_05_Register_Password_Less_Than_6_Chars() {
        registerPage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123");
        registerPage.enterToConfirmPasswordTextbox("123");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getPasswordErrorMsg(), "<p>must meet the following rules: </p><ul><li>must have at least 6 characters and not greater than 64 characters</li></ul>");
    }

    @Test
    public void TC_06_Register_Invalid_Confirm_Password() {
        registerPage.clickToRegisterLink();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToPasswordTextbox("123456");
        registerPage.enterToConfirmPasswordTextbox("123431");

        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getConfirmPasswordErrorMsg(), "The password and confirmation password do not match.");
    }

    @Test
    public void TC_07_Login() {
        registerPage.clickToLoginLink();
        loginPage = new LoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);

        loginPage.clickToLoginButton();
        customerInfoPage = new CustomerInfoPageObject(driver);
    }

    @Test
    public void TC_08_MyAccount() {
        customerInfoPage.clickToMyAccountLink();

        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(), firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(), lastName);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(), emailAddress);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserName();
    }

    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private RegisterPageObject registerPage;
    private CustomerInfoPageObject customerInfoPage;

}
