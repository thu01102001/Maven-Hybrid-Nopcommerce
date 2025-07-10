package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterButton() {
        waitElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMsg() {
        waitElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MSG);
    }

    public String getLastNameErrorMsg() {
        waitElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MSG);
    }

    public String getEmailErrorMsg() {
        waitElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MSG);
    }

    public String getConfirmPasswordErrorMsg() {
        waitElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MSG);
    }

    public void clickToRegisterLink() {
        waitElementClickable(driver, RegisterPageUI.REGISTER_LINK);
        clickToElement(driver, RegisterPageUI.REGISTER_LINK);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementClickable(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementClickable(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void enterToEmailTextbox(String email) {
        waitElementClickable(driver, RegisterPageUI.EMAIL_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
    }

    public void enterToPasswordTextbox(String password) {
        waitElementClickable(driver, RegisterPageUI.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String cfpassword) {
        waitElementClickable(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, cfpassword);
    }

    public String getRegisterSucessMsg() {
        waitElementVisible(driver, RegisterPageUI.REGISTER_SUCESS_MSG);
        return getElementText(driver,  RegisterPageUI.REGISTER_SUCESS_MSG);
    }

    public void clickToLogoutLink() {
        waitElementClickable(driver, RegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, RegisterPageUI.LOGOUT_LINK);
    }

    public String getEmailAlreadyExistsMsg() {
        waitElementVisible(driver, RegisterPageUI.EMAIL_ALREADY_EXISTS_MSG);
        return getElementText(driver, RegisterPageUI.EMAIL_ALREADY_EXISTS_MSG);
    }

    public String getPasswordErrorMsg() {
        waitElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
        return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
    }

    public void clickToLoginLink() {
        waitElementClickable(driver, RegisterPageUI.LOGIN_LINK);
        clickToElement(driver, RegisterPageUI.LOGIN_LINK);
    }
}
