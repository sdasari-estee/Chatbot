package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
    features = "src/test/resources/features/Shipped_partial_oos.feature",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports-Shipped_partial_oos.html"},
    monochrome = true
)
    
public class Shipped_partial_oos extends AbstractTestNGCucumberTests{

}
