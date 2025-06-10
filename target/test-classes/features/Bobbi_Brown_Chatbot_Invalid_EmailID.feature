 Feature: Track Order Status via Chatbot
 
 Scenario Outline: User tracks order using valid Order ID, Invalid Email
    Given the user is on the Bobbi Brown customer service page
    When the user accepts cookies
    And the user opens the chatbot
    And the user validate the chatbot header
    And the user validate the logo of BB is visible
    And the user validate the welcome message by chatbot
    And the user clicks on privacy policy and it should to redirect to policy details page
    And the user selects "TRACK MY ORDER"
    And the user confirms they have an order number
    And Validating the reply given by chatbot for order number
    And the user enters order ID "<orderId>"
    And Validating the reply given by chatbot for email
    And the user enters Invalid email "<Invalid_email>"
    And the user should get invalid email message
    Then closed the website
    
    Examples:
      | orderId    | Invalid_email                | 
      | 110059322  | valchild.test@icloud.com		  | 
      
      
 #Scenario Outline: User tracks order using valid Order ID, Invalid Email
    #Given the user is on the Bobbi Brown customer service page
    #When the user accepts cookies
    #And the user opens the chatbot
    #And the user validate the chatbot header
    #And the user validate the logo of BB is visible
    #And the user validate the welcome message by chatbot
    #And the user clicks on privacy policy and it should to redirect to policy details page
    #And the user selects "TRACK MY ORDER"
    #And the user confirms they have an order number
    #And Validating the reply given by chatbot for order number
    #And the user enters order ID "<orderId>"
    #And Validating the reply given by chatbot for email
    #And the user enters Invalid email format "<Invalid_email_format>"
    #And the user should get invalid email format message
    #
    #Examples:
      #| orderId    | Invalid_email_format        | 
      #| 110059322  | Test.com		  					 		| 