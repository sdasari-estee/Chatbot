
package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/In_warehouse.feature",
    		glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports-Cancelled.html"},
    monochrome = true
)

public class In_warehouse extends AbstractTestNGCucumberTests {

}