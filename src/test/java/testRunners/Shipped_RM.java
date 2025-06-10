package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Shipped_RM.feature",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports-Shipped_RM.html"},
    monochrome = true
)

public class Shipped_RM extends AbstractTestNGCucumberTests {

}
