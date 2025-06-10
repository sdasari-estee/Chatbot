//GCCSAL-T873 (1.0)/CC-14880 [MSAI] Chatbot rollout to BB UK

package stepDefinitions;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import org.openqa.selenium.*;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.cucumber.java.en.*;

//import io.qameta.allure.Step;




import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;

import static hooks.ExtentHooks.test;

public class TrackOrderSteps_Valid_Input {

    WebDriver driver;
    WebDriver c_driver;
    WebDriverWait wait;
    WebElement enter_textarea, send_button;

    @Given("the user is on the Bobbi Brown customer service page")
    public void user_on_customer_service_page() {
        driver = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://wwwtmp.bobbibrown.co.uk/customer-service-corporate#contact-manufacturer");
        driver.manage().window().maximize();
        assertTrue(driver.getTitle().contains("Bobbi Brown"));

        test.pass("The user is on the Bobbi Brown customer service page");

    }
	
    @When("the user accepts cookies")
    public void user_accepts_cookies() 
    {
    	try
    	{
    		WebElement accept = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='onetrust-accept-btn-handler']")));
    		if(accept.isDisplayed())
	        {
	            accept.click();
	            test.pass("The user accepts cookies");    
	        }
    	}
    	catch (Exception e) 
        {
            
            String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
        	test.fail("The user accepts cookies : "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
        
    }
	
    @When("the user opens the chatbot")
    public void user_opens_chatbot() throws InterruptedException 
    {
        
        	try 
            {
        		WebElement chatbotIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Nexus Testing')]")));
                if(chatbotIcon.isDisplayed())
                {
	        		chatbotIcon.click();
	                test.pass("The user opens the chatbot");
                }
            } 
            catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
        Thread.sleep(2000);
    }
    
    @When("the user validate the chatbot header")
    public void the_user_validate_the_chatbot_header() 
    {
    	
    	try 
    	{
    		String chatbot_header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp-top-heading-maximized\"]/span"))).getText();
	    	if(chatbot_header.contains("Message us") ||chatbot_header.contains("Virtual Assistant") ||chatbot_header.contains("Test Virtual Agent"))
	    	{
	    		test.pass("The user validate the chatbot header");
	    		
	    	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }	    
    }
    
    @When("the user validate the logo of BB is visible")
    public void the_user_validate_the_logo_of_bb_is_visible() 
    {
    		
    	try 
    	{
    		WebElement chatbot_logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='LP_LogoViewController_1']/div/span/div/img")));
    		if(chatbot_logo.isDisplayed())
	    	{
    			test.pass("The user validate the logo of BB is visible");
	    	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }
    
    @When("the user validate the welcome message by chatbot")
    public void the_user_validate_the_welcome_message_by_chatbot() 
    {	
    	
    	try
    	{
    		String welcome_msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_0']/div"))).getText();
    		if(welcome_msg.contains("Welcome to Bobbi Brown. What can we help you with?")) 
	    	{
	    		test.pass("The user validate the welcome message by chatbot");
	    	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }

    @When("the user clicks on privacy policy and it should to redirect to policy details page")
    public void the_user_clicks_on_privacy_policy_and_it_should_to_redirect_to_policy_details_page() 
    {
    	String BBWindow = driver.getWindowHandle();
    	WebElement privacy_policy = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_0']/div/a")));
    	privacy_policy.click();
    	
    	Set<String> privacyWindows = driver.getWindowHandles();
		for (String childWindow : privacyWindows)
		{
			if(!childWindow.equals(BBWindow))
			{
				driver.switchTo().window(childWindow);
				String pri_Url =driver.getCurrentUrl();
				try 
				{
					if(pri_Url.contains("privacy")) 
					{
						test.pass("The user clicks on privacy policy and it should to redirect to policy details page");
					}
				} 
				catch (Exception e) 
	            {
	            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
	            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
	                test.fail(testName +" ---> "+ e.getMessage());
	                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
	                driver.quit();
	                throw e;    
	            }
					
			}
		}
		driver.close();
		driver.switchTo().window(BBWindow);
    
    }

    @When("the user selects {string}")
    public void user_selects_option(String option) 
    {
       
        try 
    	{
        	WebElement trackOrderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='LP_TranscriptViewController_1']//li[1]/button[@title='" + option + "']")));
    		if(trackOrderBtn.isDisplayed())
	    	{
    			trackOrderBtn.click();
    			test.pass("The user selects TRACK MY ORDER");
	    	}
    	}
        catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }

    @When("the user confirms they have an order number")
    public void user_confirms_order_number() 
    {
        try 
    	{
        	String orderno_reply = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_2']/div"))).getText();
            
            if(orderno_reply.contains("Do you have your order number?")) 
        	{
            	 WebElement haveOrderBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@title='Have Order Number']")));
         		if(haveOrderBtn.isDisplayed())
     	    	{
         			 haveOrderBtn.click();
         			test.pass("The user confirms they have an order number");
     	    	}
        	}      
    	}
        catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
        
    }
    
    @When("Validating the reply given by chatbot for order number")
    public void Validating_the_reply_given_by_chatbot_for_order_number()
    {
    		try 
    		{
    			WebElement Reply_for_Have_order_numberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_4']/div")));
    	    	String Reply_for_Have_order_number = Reply_for_Have_order_numberElement.getText();

    	    	String Reply1 = "Great. Please enter the numerical order number found on your order confirmation email.";
    	    	String Reply2 = "Perfect. Enter the numeric order number from your confirmation email.";
    	    	String Reply3 = "Perfect. Please enter the numerical order number found in your order confirmation email.";
    	    	String Reply4 = "Great. To proceed, please enter the numerical order number from your confirmation email";
    	    	
    			if (Reply_for_Have_order_number.contains(Reply1) || Reply_for_Have_order_number.contains(Reply2) || 
    		    	Reply_for_Have_order_number.contains(Reply3) || Reply_for_Have_order_number.contains(Reply4)) 
    		    {
    				test.pass("Validating the reply given by chatbot for order number");  	    
    		    } 
			} 
    		catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
    	    
    	     
    }
    
    @When("the user enters order ID {string}")
    public void user_enters_order_id(String orderId)
    {
      try 
      {
    	  enter_textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
          enter_textarea.sendKeys(orderId);

          send_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='send_button_image']")));
          send_button.click();
          
          test.pass("The user enters order ID ");
      } 
      catch (Exception e) 
      {
      	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
      	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
          test.fail(testName +" ---> "+ e.getMessage());
          ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
          driver.quit();
          throw e;    
      }
        
    }
    
    @When("Validating the reply given by chatbot for email")
    public void validating_the_reply_given_by_chatbot_for_email() 
    {
    	try
    	{
	    	String Email_reply=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_6']/div"))).getText();
	        if(Email_reply.contains("Thank you! To validate the order details, please enter the email address used to place the order.")) 
	     	{
	        	 test.pass("Validating the reply given by chatbot for email");
	     	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
     	
    }
   
    @When("the user enters email {string}")
    public void user_enters_email(String email) 
    {	
    	try 
    	{
	    	enter_textarea.sendKeys(email);
	        send_button.click();
	        test.pass("The user enters email");
		
    	} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
       
    }
    
    @When("Validating the reply given by chatbot for postal code")
    public void validating_the_reply_given_by_chatbot_for_postal_code() 
    {  	 
    	try 
    	{
	    	String postalcode_reply=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_8']/div"))).getText(); 
	     	if(postalcode_reply.contains("And the billing address postal code"))
	      	{
	     		 test.pass("Validating the reply given by chatbot for postal code");
	      	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }      	
    }
    
    @When("the user enters postal code {string}")
    public void user_enters_postal_code(String postalCode)
    {
    	try 
    	{
    		enter_textarea.sendKeys(postalCode);
    	    send_button.click();
    	    test.pass("The user enters postal code");
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }
    
    @When("Validating the reply given by chatbot looking for order information")
    public void validating_the_reply_given_by_chatbot_looking_for_order_information() 
    {
    	try 
    	{
	    	WebElement reply_of_looking_for_details_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_10']/div")));
	    	String reply_of_looking_for_details = reply_of_looking_for_details_Element.getText();
	
	    	String MSg1 = "Thank you. We are looking up your order details now. This may take a few moments.";
	    	String MSg2 = "Thank you. We are looking up your order information now. This will take a few moments.";
	    	String MSg3 = "Thanks. We're getting your order details now, it will only take a moment.";
	    	String MSg4 = "Thank you. We are looking up your order information now. Please allow us a few moments to gather the details.";
	    	String MSg5 = "Thanks. We're looking up your order information now. This will take a few moments.";
	
	    	if (reply_of_looking_for_details.contains(MSg1) || reply_of_looking_for_details.contains(MSg2) || 
	    	    reply_of_looking_for_details.contains(MSg3) || reply_of_looking_for_details.contains(MSg4) ||reply_of_looking_for_details.contains(MSg5)) 
	    	{
	    		test.pass("Validating the reply given by chatbot looking for order information");   
	    	} 
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }

    }
    
    @When("retrived the current status of the order")
    public void retrived_the_current_status_of_the_order() 
    {
    	try 
    	{
	    	String Status =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_11\"]/div"))).getText();         
	    	//System.out.println(Status);
	
			String[] lines = Status.split("\\r?\\n"); // splits by line breaks
			for (String line : lines) 
			{
			    if (line.contains("cancelled") || line.contains("on its way") || line.contains("out of stock") ||line.contains("on it's way")) 
			    {
			    	test.pass("Retrived the current status of the order"); 
			    }			    
			}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }
    
    @When("Valdating the reply given by chatbot for further assistance and clicking on Yes")
    public void valdating_the_reply_given_by_chatbot_for_further_assistance_and_clicking_on_Yes() 
    {
    	try
    	{
	    	WebElement reply_futher_assistance_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_12\"]/div")));
	    	String reply_futher_assistance = reply_futher_assistance_Element.getText();
	
	    	String R_assistance1 = "Was this everything you were looking for today?";
	    	String R_assistance2 = "Was this all the information you needed for today?";
	    	String R_assistance3 = "Did this information provide everything you need today?";
	    	String R_assistance4 = "Did this help with everything you needed today?";
    	
	    	if (reply_futher_assistance.contains(R_assistance1) || reply_futher_assistance.contains(R_assistance2) || 
	    		reply_futher_assistance.contains(R_assistance3) || reply_futher_assistance.contains(R_assistance4)) 
	    	{
	    		test.pass("Valdating the reply given by chatbot for further assistance and clicking on Yes");     
	    	} 
	    	WebElement Click_yes = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"LP_TranscriptViewController_1\"]//li[1]/button")));
	    	Click_yes.click();
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    	
    }
    
    @When("Validating the reply given by chatbot THANK YOU")
    public void validating_the_reply_given_by_chatbot_thank_you() 
    {
    	try
    	{
	    	WebElement reply_THANK_YOU_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_14']/div")));
	    	String  reply_THANK_YOU = reply_THANK_YOU_Element.getText();
	
	    	String R_1 = "Great! Glad we could help - have a lovely day.";
	    	String R_2 = "Perfect! It was a pleasure helping you – have a great day!";
	    	String R_3 = "Wonderful! So glad we could assist – have a fantastic day.";
	    	String R_4 = "Awesome! We're happy to have helped – enjoy the rest of your day";
    	
	    	if (reply_THANK_YOU.contains(R_1) || reply_THANK_YOU.contains(R_2) || reply_THANK_YOU.contains(R_3) || reply_THANK_YOU.contains(R_4) )
	    	{
	    		test.pass("Validating the reply given by chatbot THANK YOU");    
	    	}
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    @When("Validating the reply about the service experience")
    public void validating_the_reply_about_the_service_experience() 
    {
    	try
    	{
	    	WebElement reply_service_experience_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_16']/div")));
	    	String  reply_service_experience = reply_service_experience_Element.getText();
	
	    	String R_S_1 = "How would you rate the service experience you just had?";
	
	    	if (reply_service_experience.contains(R_S_1) )
	    	{
	    		test.pass("Validating the reply about the service experience");
	    	} 
    	}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    }
    @When("giving the rating as five star")
    public void giving_the_rating_as_five_star() 
    {
    	try 
    	{
    		WebElement Click_Five_star = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li[5]/button")));
        	Click_Five_star.click();
        	test.pass("Giving the rating as five star");
        	
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    @When("Validating the replay about feedback")
    public void validating_the_replay_about_feedback() 
    {
    	try 
    	{
    		WebElement reply_feedback_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_18']/div")));
        	String  reply_feedback = reply_feedback_Element.getText();

        	String R_F_1 = "Please share feedback about your experience with our automated assistant.";

        	if (reply_feedback.contains(R_F_1) )
        	{
        		test.pass("Validating the replay about feedback");    
        	} 
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    @When("giving the feedback as GOOD")
    public void giving_the_feedback_as_good() 
    {
    	try 
    	{
    		enter_textarea.sendKeys("GOOD");
        	send_button.click();
        	test.pass("Giving the feedback as GOOD");
        	
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    @When("Validaing the Thank you msg by chatbot")
    public void validaing_the_thank_you_msg_by_chatbot() 
    {
    	try 
    	{
    		WebElement reply_Thank_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_20']/div")));
        	String  reply_Thank = reply_Thank_Element.getText();

        	String R_C_1 = "Thanks for Chatting with us!";

        	if (reply_Thank.contains(R_C_1) )
        	{
        		test.pass("Validaing the Thank you msg by chatbot");
        	} 
		}
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    
    @Then("closed the website")
    public void closed_the_website() 
    {
    	try 
    	{
    		test.pass("Closed the website");
    		driver.quit();
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
    	
    }
    
    //=====================================================================================================================================
    // Invalid Order ID
    //=====================================================================================================================================
    
    @When("the user enters invalid order ID {string}")
	public void the_user_enters_invalid_order_id(String invalid_orderId) 
	{
    	
        try 
        {
        	enter_textarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@data-lp-cust-id='input_text']")));
            enter_textarea.sendKeys(invalid_orderId);
            send_button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-lp-point='send_button_image']")));
            send_button.click(); 
            test.pass("The user enters invalid order ID ");
		} 
        catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	@When("the user should get invalid order Id reply from chatbot and another try")
	public void the_user_should_get_invalid_order_id_reply_from_chatbot_and_another_try() 
	{
    	try 
        {
    		WebElement invalid_reply_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_6\"]/div")));
        	String invalid_reply = invalid_reply_Element.getText();

        	String MSg1_invaild= "The order number doesn't look quite right. Your numerical order number can be found in your Order confirmation email. Please try again.";
        	String MSg2_invaild = "It seems like the order number isn’t quite right. You can find your numerical order number in your order confirmation email. Please give it another try!";
        	String MSg3_invaild = "That order number doesn’t look correct. Your numerical order number should be in your order confirmation email. Could you please check and try again?";
        	String MSg4_invaild = "It looks like the order number is incorrect. You can find the numerical order number in your order confirmation email. Please try entering it again!";
        	String MSg5_invalid = "The order number doesn't look quite right. Order Id\n"+"Your numerical order number can be found in your Order confirmation email.\n"+"Please try again.";
        	
        	if (invalid_reply.contains(MSg1_invaild) || invalid_reply.contains(MSg2_invaild) || 
        			invalid_reply.contains(MSg3_invaild) || invalid_reply.contains(MSg4_invaild) || invalid_reply.contains(MSg5_invalid) ) 
        	{
        		test.pass("The user should get invalid order Id reply from chatbot and another try");
        	}  
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	@When("the user enters again invalid order Id {string}")
	public void the_user_enters_again_invalid_order_id(String invalid_orderId) 
	{
		try 
        {
			 enter_textarea.sendKeys(invalid_orderId);
			 send_button.click();
			 test.pass("The user enters again invalid order Id");
		} 
		catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }	
		 
		 
	}
	
	@When("the user should get sorry messege and redirect to advisor if avaliable")
	public void the_user_should_get_sorry_messege_and_redirect_to_advisor_if_avaliable() 
	{
    	try 
        {
    		WebElement invalid_reply_Element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_8\"]/div")));
        	String invalid_reply2 = invalid_reply_Element2.getText();

        	String MSg1_invaild2= "We're sorry, our automated service can't help you right now. Checking to see if there is an advisor available to help.";
        	String MSg2_invaild2 = "We’re sorry, our automated service isn’t able to assist right now. Let me check if an advisor is available to help you.";
        	String MSg3_invaild2 = "Apologies, our automated service can't help at the moment. I’m checking to see if an advisor is available to assist.";
        	String MSg4_invaild2= "Unfortunately our automated service isn’t able to assist right now. I’ll check if an advisor is free to help you.";
        	
        	if (invalid_reply2.contains(MSg1_invaild2) || invalid_reply2.contains(MSg2_invaild2) || 
        			invalid_reply2.contains(MSg3_invaild2) || invalid_reply2.contains(MSg4_invaild2) ) 
        	{
        		test.pass("The user should get sorry messege and redirect to advisor if avaliable");	    
        	}
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	//=====================================================================================================================================
	// Invalid Email ID
    //=====================================================================================================================================
   
	
	@When("the user enters Invalid email {string}")
	public void the_user_enters_invalid_email(String Inavlid_EmailID) 
	{
		try 
        {
			enter_textarea.sendKeys(Inavlid_EmailID);
	        send_button.click();
	        test.pass("The user enters Invalid email");
		} 
		catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	@When("the user should get invalid email message")
	public void the_user_should_get_invalid_email_message() 
	{
    	try 
        {
    		WebElement invalid_email_reply_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_8\"]/div")));
        	String invalid_email_reply = invalid_email_reply_Element.getText();

        	String MSg1_invaild_email= "The email address entered isn't valid, please check it is the one used to place the order and that it is a valid format for example: your.name@email.com.";
        	String MSg2_invaild_email = "It looks like the email address you entered isn't valid. Please double-check that it’s the one used to place the order and follows the correct format, like your.name@email.com.";
        	String MSg3_invaild_email = "The email address you’ve entered doesn’t seem quite right. Please make sure it’s the one used to place the order and that it’s in the right format, for example: your.name@email.com.";
        	String MSg4_invaild_email= "Oops! The email address doesn’t appear to be valid. Please verify it’s the one used to place the order and in the correct format (e.g., your.name@email.com)";
        	

        	if (invalid_email_reply.contains(MSg1_invaild_email) || invalid_email_reply.contains(MSg2_invaild_email) || 
        			invalid_email_reply.contains(MSg3_invaild_email) || invalid_email_reply.contains(MSg4_invaild_email) ) 
        	{
        		test.pass("The user should get invalid email message");   
        	}
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	@When("the user enters Invalid email format {string}")
	public void the_user_enters_invalid_email_format(String Inavlid_EmailID_format) 
	{
		try 
        {
			enter_textarea.sendKeys(Inavlid_EmailID_format);
	        send_button.click();
	        test.pass("The user enters Invalid email format");   
		} 
		catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
	@When("the user should get invalid email format message")
	public void the_user_should_get_invalid_email_format_message() 
	{
    	try 
        {
    		WebElement invalid_email_format_reply_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lp_line_bubble_8\"]/div")));
        	String invalid_email_format_reply = invalid_email_format_reply_Element.getText();

        	String MSg1_invaild_email_format= "We're sorry, our automated service can't help you right now.  Checking to see if there is an advisor available to help.";
        	String MSg2_invaild_email_format = "We’re sorry, our automated service isn’t able to assist right now. Let me check if an advisor is available to help you.";
        	String MSg3_invaild_email_format = "Apologies, our automated service can't help at the moment. I’m checking to see if an advisor is available to assist.";
        	String MSg4_invaild_email_format= "Unfortunately our automated service isn’t able to assist right now. I’ll check if an advisor is free to help you.";
        	

        	if (invalid_email_format_reply.contains(MSg1_invaild_email_format) || invalid_email_format_reply.contains(MSg2_invaild_email_format) || 
        			invalid_email_format_reply.contains(MSg3_invaild_email_format) || invalid_email_format_reply.contains(MSg4_invaild_email_format) ) 
        	{
        		test.pass("The user should get invalid email format message");    
        	}
		} 
    	catch (Exception e) 
        {
        	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
        	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
            test.fail(testName +" ---> "+ e.getMessage());
            ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
            driver.quit();
            throw e;    
        }
	}
	
		//=====================================================================================================================================
		// Invalid Postal code
		//=====================================================================================================================================

		@When("the user enters invalid postal code {string}")
		public void the_user_enters_invalid_postal_code(String postalCode_invalid) 
		{
			try 
	        {
				enter_textarea.sendKeys(postalCode_invalid);
			    send_button.click();
			   
			} 
			catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
			 
		     
		}
		@When("the user should get invalid postal message from chatbot")
		public void the_user_should_get_invalid_postal_message_from_chatbot() 		
		{
			
	    	try 
	        {
	    		WebElement invalid_postalcode_reply_Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_10']/div")));
		    	String invalid_postalcode_reply = invalid_postalcode_reply_Element.getText();

		    	String MSg1_invaild_postalcode= "The postal code entered isn't valid, please check it is the one used to place the order and that the format is valid.";
		    	String MSg2_invaild_postalcode = "The postal code you entered doesn’t seem valid. Please double-check that it’s the one used to place the order and in the correct format.";
		    	String MSg3_invaild_postalcode = "Oops! The postal code you entered isn’t valid. Please ensure it’s the one used for the order and follows the correct format.";
		    	String MSg4_invaild_postalcode= "It looks like the postal code isn’t valid. Please check that it’s the one used to place the order and that it’s in the proper format.";
		    	String MSg5_invaild_postalcode ="The post code entered isn't valid, please check it is the one used to place the order and that the format is valid.";

		    	if (invalid_postalcode_reply.contains(MSg1_invaild_postalcode) || invalid_postalcode_reply.contains(MSg2_invaild_postalcode) || 
		    			invalid_postalcode_reply.contains(MSg3_invaild_postalcode) || invalid_postalcode_reply.contains(MSg4_invaild_postalcode)|| invalid_postalcode_reply.contains(MSg5_invaild_postalcode) ) 
		    	{
		    		 test.pass("The user should get invalid postal message from chatbot");	    
		    	}
			} 
	    	catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
	    	
		}
		
		@When("the user again enters the invalid postal code {string}")
		public void the_user_again_enters_the_invalid_postal_code(String postalCode_invalid) throws InterruptedException 
		{
			try 
	        {
				enter_textarea.sendKeys(postalCode_invalid);
			    send_button.click();
			    test.pass("the user again enters the invalid postal code");	
			} 
			catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
			  
			  
		      
		}
		@When("the user should get sorry message and try to advisor from chatbot")
		public void the_user_should_get_sorry_message_and_try_to_advisor_from_chatbot() 
		{
	    	try 
	        {
	    		WebElement invalid_postalcode_reply_Element2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_12']/div")));
		    	String invalid_postalcode_reply2 = invalid_postalcode_reply_Element2.getText();

		    	String MSg1_invaild_postalcode2= "We're sorry, our automated service can't help you right now.  Checking to see if there is an advisor available to help.";
		    	String MSg2_invaild_postalcode2 = "We’re sorry, our automated service isn’t able to assist right now. Let me check if an advisor is available to help you.";
		    	String MSg3_invaild_postalcode2 = "Apologies, our automated service can't help at the moment. I’m checking to see if an advisor is available to assist.";
		    	String MSg4_invaild_postalcode2= "Unfortunately our automated service isn’t able to assist right now. I’ll check if an advisor is free to help you.";
		    	

		    	if (invalid_postalcode_reply2.contains(MSg1_invaild_postalcode2) || invalid_postalcode_reply2.contains(MSg2_invaild_postalcode2) || 
		    		invalid_postalcode_reply2.contains(MSg3_invaild_postalcode2) || invalid_postalcode_reply2.contains(MSg4_invaild_postalcode2) ) 
		    	{
		    		test.pass("the user should get sorry message and try to advisor from chatbot");	  	    
		    	}
			} 
	    	catch (Exception e) 
            {
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(driver, testName + timestamp );
                driver.quit();
                throw e;    
            }
		}
		
		//=====================================================================================================================================
		// Invalid Postal code
		//=====================================================================================================================================
		@When("checking order status via track link provided by chatbot and closing the tab")
		public void checking_order_status_via_track_link_provided_by_chatbot_and_closing_the_tab() throws IOException, InterruptedException 
		{
			try 
			{
				WebElement Track_order_link_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='lp_line_bubble_11']/div/b[1]/a")));
			    String track_order_link = Track_order_link_element.getDomAttribute("href");
			    String TrackID =Track_order_link_element.getText();

			    // Set ChromeOptions before initializing the driver
			    ChromeOptions options = new ChromeOptions();
			    options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
			    options.addArguments("--disable-blink-features=AutomationControlled");
			    options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
			    options.setExperimentalOption("useAutomationExtension", false);
			    options.addArguments("window-size=1920,1080");

			    Map<String, Object> prefs = new HashMap<>();
			    prefs.put("credentials_enable_service", false);
			    prefs.put("profile.password_manager_enabled", false);
			    options.setExperimentalOption("prefs", prefs);

			    WebDriver c_driver = new ChromeDriver(options);
			    WebDriverWait localWait = new WebDriverWait(c_driver, Duration.ofSeconds(15));

			    Thread.sleep(2000); // simulate human delay
			    c_driver.get(track_order_link);
			    

			    if (c_driver.getTitle().contains("Royal Mail Group")) {
			        try 
			        {
			        	c_driver.manage().window().maximize();
			        	localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"consent_prompt_submit\"]"))).click();
			        	Thread.sleep(3000);
			        	localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='barcode-input']"))).sendKeys(TrackID);
			            localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"submit\"]"))).click();
			            Thread.sleep(3000);
			            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(c_driver);
						
						
						ImageIO.write(screenshot.getImage(), "PNG", new File("./screenshots/Shipped_RM_screenshot.png.png"));
						test.pass("Checking order status via track link provided by chatbot and closing the tab");	 
					    c_driver.quit();
				    }
			         
			        catch (Exception e) 
		            {
		                throw e;    
		            }
			        
			    }
			    else if(c_driver.getTitle().contains("DHLParcel"))
			    {
			    	try {
			    		c_driver.manage().window().maximize();
			    		localWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();
				    	Thread.sleep(2000);
				    	
				    	Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(c_driver);
						
						ImageIO.write(screenshot.getImage(), "PNG", new File("./screenshots/Shipped_screenshot.png"));
						test.pass("Checking order status via track link provided by chatbot and closing the tab");	 
					    c_driver.quit();
			    		
			    	}
			    	catch(Exception e) 
			    	{
		                throw e;
					}
			    	
			    }   
			} 
			catch (Exception e) 
            {
				c_driver.manage().window().maximize();
            	String testName = new Object(){}.getClass().getEnclosingMethod().getName();
            	String timestamp = new SimpleDateFormat("yyyy-MM-dd [HH-mm-ss]").format(new Date());
                test.fail(testName +" ---> "+ e.getMessage());
                ScreenshotUtil.takeScreenshot(c_driver, testName + timestamp );
                c_driver.quit();
                throw e;    
            }
		    

		   
			
		} 
}  

	
   





