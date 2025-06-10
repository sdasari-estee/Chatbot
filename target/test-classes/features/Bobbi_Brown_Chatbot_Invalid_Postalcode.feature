Feature: Track Order Status via Chatbot

  Scenario Outline: User tracks order using valid Order ID, Email, and invalid Pincode
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
    And the user enters email "<email>"
    And Validating the reply given by chatbot for postal code
    And the user enters invalid postal code "<postalCode_invalid>"
    And the user should get invalid postal message from chatbot
    And the user again enters the invalid postal code "<postalCode_invalid>"
    And the user should get sorry message and try to advisor from chatbot
    Then closed the website
    
    Examples:
      | orderId    | email                	 	  	| postalCode_invalid |
      | 110059322  | valchild@icloud.com		  		| 123ABC   				   |