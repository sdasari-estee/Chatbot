package stepDefinitions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    	public static void takeScreenshot(WebDriver driver, String testName) {
        try 
        {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destinationFile = new File("./screenshots/" + testName + ".png");
            FileUtils.copyFile(screenshot, destinationFile);
            System.out.println("Screenshot saved for failed test: " + testName);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}