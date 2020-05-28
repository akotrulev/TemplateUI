package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utility.constant.SystemPropertyNames;

import java.util.List;

/**
 * Every page object will inherit this class, giving it access to it's methods. First PO class initialisation
 * the Webdriver will be parsed via constructor
 */
public class Browser {
    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;
    private String baseUrl;

    public Browser(WebDriver webDriver) {
        baseUrl = System.getProperty(SystemPropertyNames.BASE_URL);
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(webDriver, 15, 50);
    }

    protected void navigateToURL(String url) {
        Reporter.log("Opening " + baseUrl + url);
        webDriver.get(baseUrl + url);
    }

    protected String getElementText(WebElement element) {
        Reporter.log("Getting text from element " + element.getTagName() + "and trimming white spaces");
        return element.getText().trim();
    }

    protected void writeText(WebElement element, String text) {
        Reporter.log("Clearing text from " + element.getTagName());
        element.clear();
        Reporter.log("Writing text " + text + " to element " + element.getTagName());
        element.sendKeys(text);
    }

    protected void clickElement(WebElement element) {
        Reporter.log("Clicking element " + element.getTagName());
        element.click();
    }

    public void closeDriver() {
        webDriver.close();
        webDriver = null;
    }

    protected WebElement findElementByLocator(By locator) {
        waitForElementWithLocator(locator);
        return webDriver.findElement(locator);
    }

    protected List<WebElement> findElementsByLocator(By locator) {
        waitForElementWithLocator(locator);
        return webDriver.findElements(locator);
    }

    protected WebElement findElementByLocatorWithRood(WebElement root, By locator) {
        //TODO add wait?
        return root.findElement(locator);
    }

    protected List<WebElement> findElementsByLocatorWithRood(WebElement root, By locator) {
        //TODO add wait?
        return root.findElements(locator);
    }

    protected void waitForElementWithLocator(By locator) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForElement(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15, 50);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void addCookieToBrowser(Cookie cookie) {
        navigateToURL("");
        webDriver.manage().addCookie(cookie);
    }
}
