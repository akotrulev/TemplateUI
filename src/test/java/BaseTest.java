import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.Browser;
import utility.BrowserTypeEnum;
import utility.CustomQueue;
import utility.SystemPropertyNames;

import java.util.Queue;

public class BaseTest {
    protected Queue<WebDriver> webDriverQueue;

    @BeforeClass
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
        int numberOfBrowsers = iTestContext.getSuite().getAllMethods().size();
        for (int i = 0; i < numberOfBrowsers; i++) {
            webDriverQueue.add(Browser.createDriver());
        }
    }

    @Test
    public void createWebDriver() {
        webDriverQueue.poll();
    }
}
