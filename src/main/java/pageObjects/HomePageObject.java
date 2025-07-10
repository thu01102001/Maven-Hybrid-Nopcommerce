package pageObjects;

import core.BasePage;
import core.BaseTest;
import org.openqa.selenium.WebDriver;
import pageUIs.HomePageUI;
import pageUIs.RegisterPageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToRegisterLink() {
        waitElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickToElement(driver, HomePageUI.REGISTER_LINK);
    }
}
