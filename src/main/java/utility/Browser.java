package utility;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.net.URL;
import java.util.List;

/**
 * This is a common class with implementation for the main actions used in a browser.
 * There will be a class for each browser (chrome, firefox, etc.) which will extend this one
 * as to avoid repetition of these basic action methods
 */
public class Browser {

    private String baseURL;

    protected WebDriver webDriver;
    protected WebDriverWait webDriverWait;

    public static WebDriver createDriver() {
        // Get the browser type specified in system properties
        BrowserTypeEnum browserTypeEnum = BrowserTypeEnum.valueOf(System.getProperty(SystemPropertyNames.BROWSER_TYPE));

        // Set the needed system property of the specific browser
        System.setProperty(browserTypeEnum.getProperty(), browserTypeEnum.getPath());

        // Return the needed instance for the specific browser
        return browserTypeEnum.getRemoteWebDriver().get();
    }


    public WebDriver getWebDriver() {
        return webDriver;
    }

    public Browser(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 15, 50);
        this.baseURL = System.getProperty(SystemPropertyNames.BASE_URL);
    }

    public void navigateToURL(String url) {
        Reporter.log("Opening " + baseURL + url);
        webDriver.get(baseURL + url);
    }

    public String getElementText(WebElement element) {
        Reporter.log("Getting text from element " + element.getTagName() + "and trimming white spaces");
        return element.getText().trim();
    }

    public void writeText(WebElement element, String text) {
        Reporter.log("Clearing text from " + element.getTagName());
        element.clear();
        Reporter.log("Writing text " + text + " to element " + element.getTagName());
        element.sendKeys(text);
    }

    public void clickElement(WebElement element) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        Reporter.log("Clicking element " + element.getTagName());
        element.click();
    }

    public void closeDriver() {
        webDriver.close();
        webDriver = null;
    }

    public void waitForAjax(WebDriver driver) {
        while (true) {
            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            if (ajaxIsComplete) {
                break;
            }
        }
    }

    public WebElement findElementByLocator(By locator) {
        waitForElementWithLocator(locator);
        return webDriver.findElement(locator);
    }

    public List<WebElement> findElementsByLocator(By locator) {
        waitForElementWithLocator(locator);
        return webDriver.findElements(locator);
    }

    public WebElement findElementByLocatorWithRood(WebElement root, By locator) {
        //TODO add wait?
        return root.findElement(locator);
    }

    public List<WebElement> findElementsByLocatorWithRood(WebElement root, By locator) {
        //TODO add wait?
        return root.findElements(locator);
    }

    public void waitForElementWithLocator(By locator) {
        waitForJs();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitForElement(WebElement webElement) {
        waitForJs();
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15, 50);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForJs() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 15, 50);
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        webDriverWait.until(jsLoad);
    }

    public void addCookieToBrowser(Cookie cookie) {
        navigateToURL("");
        webDriver.manage().addCookie(cookie);
    }
}
