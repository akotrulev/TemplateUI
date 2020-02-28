package cucumberrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/feature/BasicTest.feature", glue = "step")
public class ExampleTestRunnerClass extends AbstractTestNGCucumberTests {
}
