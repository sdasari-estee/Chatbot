package testRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features/Bobbi_Brown_Chatbot_Invalid_Postalcode.feature",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports-invalid-postalcode.html"},
    monochrome = true
)
public class TestRunner_Invalid_Postalcode extends AbstractTestNGCucumberTests{
}
