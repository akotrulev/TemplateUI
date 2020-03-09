package testng;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageobject.ExamplePageObject;
import utility.Browser;
import utility.enumeration.BrowserTypeEnum;
import utility.CustomQueue;
import utility.constant.SystemPropertyNames;

import java.util.Queue;

public class BaseTest {
    protected Queue<WebDriver> webDriverQueue;

    @BeforeSuite
    public void createWebDrivers(ITestContext iTestContext) {
        // If base url is not set, default to stage url
        if (System.getProperty(SystemPropertyNames.BASE_URL) == null) {
            System.setProperty(SystemPropertyNames.BASE_URL, "http://10.0.4.200:3000");
        }
        // If browser type is not set, default to Chrome
        if (System.getProperty(SystemPropertyNames.BROWSER_TYPE) == null) {
            System.setProperty(SystemPropertyNames.BROWSER_TYPE, BrowserTypeEnum.CHROME.name());
        }
        // Add web drivers to queue
        webDriverQueue = new CustomQueue<>();
        //TODO this can be changed to accept number of browsers and create different queues based on test groups
        int numberOfBrowsers = iTestContext.getSuite().getAllMethods().size();
        for (int i = 0; i < numberOfBrowsers; i++) {
            webDriverQueue.add(Browser.createDriver());
        }
    }

    @AfterSuite
    public void closeAllDrivers() {
        webDriverQueue.forEach(WebDriver::close);
    }
}
