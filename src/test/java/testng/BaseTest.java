package testng;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utility.CustomQueue;
import utility.constant.SystemPropertyNames;
import utility.enumeration.BrowserTypeEnum;

import java.util.Queue;

public class BaseTest {
    protected CustomQueue<WebDriver> webDriverQueue;

    @BeforeSuite
    public void createWebDrivers(ITestContext iTestContext) {
        // If base url is not set, default to prod url
        if (System.getProperty(SystemPropertyNames.BASE_URL) == null) {
            System.setProperty(SystemPropertyNames.BASE_URL, "https://www.google.com");
        }

        // If browser type is not set, default to Chrome
        if (System.getProperty(SystemPropertyNames.BROWSER_TYPE) == null) {
            System.setProperty(SystemPropertyNames.BROWSER_TYPE, BrowserTypeEnum.CHROME.name());
        }

        // Get and store the browser type
        BrowserTypeEnum browserType = BrowserTypeEnum.valueOf(System.getProperty(SystemPropertyNames.BROWSER_TYPE));

        // Set the needed properties for selenium to be able to open a new browser
        System.setProperty(browserType.getProperty(), browserType.getPath());

        // Add web drivers to queue
        webDriverQueue = new CustomQueue<>();
        //TODO this can be changed to accept number of browsers and create different queues based on test groups
        int numberOfBrowsers = iTestContext.getSuite().getAllMethods().size();
        for (int i = 0; i < numberOfBrowsers; i++) {
            webDriverQueue.add(browserType.getRemoteWebDriver().get());
        }
    }

    @AfterSuite
    public void closeAllDrivers() {
        webDriverQueue.forEach(WebDriver::close);
    }
}
