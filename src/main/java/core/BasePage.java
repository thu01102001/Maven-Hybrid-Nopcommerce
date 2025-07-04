package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private WebDriver driver;
    private final long SHORT_TIMEOUT = 10;
    private final long LONG_TIMEOUT = 30;

    public static BasePage getInstance() {
        return new BasePage();
    }

    public void openPageUrl(WebDriver driver, String pageUrl) {
        driver.get(pageUrl);
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.alertIsPresent());
    }

    public void acceptToAlert(WebDriver driver) {
        //vừa wait vừa switch luôn
        waitAlertPresence(driver).accept();
    }

    public void cancelToAlert(WebDriver driver) {
        //vừa wait vừa switch luôn
        waitAlertPresence(driver).dismiss();
    }

    public void sendkeyToAlert(WebDriver driver, String keyToSend) {
        //vừa wait vừa switch luôn
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        //vừa wait vừa switch luôn
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSecond(int timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchToWindowByID(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                break;
            }
        }

    }

    public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void switchToWindowByContainTitle(WebDriver driver, String expectedTitle) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            driver.switchTo().window(window);
            if (driver.getTitle().contains(expectedTitle)) {
                break;
            }
        }
        sleepInSecond(2);
    }

    public void closeAllExceptMain(WebDriver driver, String windowID) {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(windowID)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendkeyToElement(WebDriver driver, String locator, String keyToSend) {
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public String getSelectedItemInDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).isMultiple();
    }

    public void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem) {
        clickToElement(driver, parentLocator);
        sleepInSecond(1);

        new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        List<WebElement> allItems = getListElement(driver, childLocator);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)) {
                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getDomProperty(propertyName);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public String getElementCss(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaColor) {
        return Color.fromString(rgbaColor).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckbox(WebDriver driver, String locator) {
        if(!isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if(isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnable(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
    }

    public void moveToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
    }

    public void dragAnđrop(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void snedKeyBoardToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), keys).perform();
    }

    public Object executeForBrowser(WebDriver driver, String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

//    public boolean isExpectedTextInInnerText(WebDriver driver, String textExpected) {
//        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
//        return textActual.equals(textExpected);
//    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

//    public void navigateToUrlByJS(WebDriver driver, String url) {
//        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
//        sleepInSecond(3);
//    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSecond(3);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                        getWebElement(driver, locator));
    }

    public WebElement waitElementVisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementVisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitElementSelected (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public WebElement waitElementClickable (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public boolean waitElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitListElementInvisible (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfAllElements(getListElement(driver, locator)));
    }

    public WebElement waitElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementPresence (WebDriver driver, String locator){
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIMEOUT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

}
