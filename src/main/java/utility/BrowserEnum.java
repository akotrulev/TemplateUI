package utility;

import org.openqa.selenium.remote.BrowserType;

public enum BrowserEnum {
    CHROME(BrowserType.CHROME, "webdriver.chrome.driver", "src/test/resources/chromedriver.exe"),
    FIREFOX(BrowserType.FIREFOX, "webdriver.gecko.driver", "src/test/resources/"),
    IE(BrowserType.IE, "webdriver.ie.driver", "src/test/resources/"),
    EDGE(BrowserType.EDGE, "webdriver.edge.driver", "src/test/resources/"),
    SAFARI(BrowserType.SAFARI, "webdriver.safari.driver", "src/test/resources/"),
    OPERA(BrowserType.OPERA_BLINK, "webdriver.opera.driver", "src/test/resources/");

    BrowserEnum(String browserType, String property, String path) {
        this.property = property;
        this.path = path;
        this.browserType = browserType;
    }

    private String browserType;
    private String property;
    private String path;

    public String getBrowserType() {
        return browserType;
    }

    public String getProperty() {
        return property;
    }

    public String getPath() {
        return path;
    }
}
