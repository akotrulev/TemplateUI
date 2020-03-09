package testng;

import org.testng.annotations.Test;
import pageobject.ExamplePageObject;

public class ExampleTestNgTest extends BaseTest {
    @Test
    public void createWebDriver() {
        ExamplePageObject examplePageObject = new ExamplePageObject(webDriverQueue.poll());
    }
}
