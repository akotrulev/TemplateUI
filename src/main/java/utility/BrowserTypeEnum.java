package utility;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.function.Supplier;

public enum BrowserTypeEnum {
    CHROME(BrowserType.CHROME, ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "src/test/resources/chromedriver.exe", ChromeDriver::new),
    FIREFOX(BrowserType.FIREFOX, GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, "src/test/resources/", FirefoxDriver::new),
    IE(BrowserType.IE, InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, "src/test/resources/", InternetExplorerDriver::new),
    EDGE(BrowserType.EDGE, EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "src/test/resources/", EdgeDriver::new),
    //TODO can't find the property in SafariDriverService class (all others extend DriverService class)
    SAFARI(BrowserType.SAFARI, "webdriver.safari.driver", "src/test/resources/", SafariDriver::new),
    OPERA(BrowserType.OPERA_BLINK, OperaDriverService.OPERA_DRIVER_EXE_PROPERTY, "src/test/resources/", OperaDriver::new);

    BrowserTypeEnum(String browserType, String property, String path, Supplier<RemoteWebDriver> remoteWebDriver) {
        this.property = property;
        this.path = path;
        this.browserType = browserType;
        this.remoteWebDriver = remoteWebDriver;
    }

    private String browserType;
    private String property;
    private String path;
    private Supplier<RemoteWebDriver> remoteWebDriver;

    public String getBrowserType() {
        return browserType;
    }

    public String getProperty() {
        return property;
    }

    public String getPath() {
        return path;
    }

    public Supplier<RemoteWebDriver> getRemoteWebDriver() {
        return remoteWebDriver;
    }
}
