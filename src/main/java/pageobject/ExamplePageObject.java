package pageobject;

import locator.ExamplePageLocators;
import org.openqa.selenium.WebDriver;
import utility.Browser;

public class ExamplePageObject extends Browser {
    public ExamplePageObject(WebDriver webDriver) {
        super(webDriver);
    }

    public void interactWithExampleElement() {
        clickElement(findElementByLocator(ExamplePageLocators.elementById));
    }
}
