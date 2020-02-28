package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;

/**
 * This is implementation specific for chrome,
 */
public class ChromeBrowser {

    private WebDriver driver;

    public WebDriver getDriver() {
        if ( driver == null ) {
            System.setProperty( "webdriver.chrome.driver" , "src/test/resources/chromedriver.exe" );
            Reporter.log("Initialising WebDriver");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }



}
