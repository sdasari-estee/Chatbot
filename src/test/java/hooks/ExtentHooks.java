package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;


public class ExtentHooks{
    public static ExtentTest test;

    @Before
    public void beforeScenario(Scenario scenario) {
        test = ExtentReportManager.createTest(scenario.getName());
    }

    @After
    public void afterScenario() {
        ExtentReportManager.flush();
    }
}





