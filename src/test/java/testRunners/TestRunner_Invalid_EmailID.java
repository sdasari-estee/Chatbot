package testRunners;



import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features/Bobbi_Brown_Chatbot_Invalid_EmailID.feature",
    glue = {"stepDefinitions", "hooks"},
    plugin = {"pretty", "html:target/cucumber-reports-invalid-EmailID.html"},
    monochrome = true
)
public class TestRunner_Invalid_EmailID {
}
