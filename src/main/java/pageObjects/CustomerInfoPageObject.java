package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.CustomerInfoPageUI;

public class CustomerInfoPageObject extends BasePage {
    private WebDriver driver;

    public CustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToMyAccountLink() {
        waitElementClickable(driver, CustomerInfoPageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, CustomerInfoPageUI.MY_ACCOUNT_LINK);
    }

    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX);
        return getElementDOMProperty(driver, CustomerInfoPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX);
        return getElementDOMProperty(driver, CustomerInfoPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getEmailTextboxValue() {
        waitElementVisible(driver, CustomerInfoPageUI.EMAIL_TEXTBOX);
        return getElementDOMProperty(driver, CustomerInfoPageUI.EMAIL_TEXTBOX, "value");
    }
}
