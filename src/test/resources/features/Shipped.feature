Feature: Track Order Status via Chatbot

  Scenario Outline: User tracks order using valid Order ID, Email, and Pincode which is Shipped
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
    And the user enters postal code "<postalCode>"
    And Validating the reply given by chatbot looking for order information 
    And retrived the current status of the order
    And checking order status via track link provided by chatbot and closing the tab
    And Valdating the reply given by chatbot for further assistance and clicking on Yes
    And Validating the reply given by chatbot THANK YOU 
    And Validating the reply about the service experience
    And giving the rating as five star
    And Validating the replay about feedback
    And giving the feedback as GOOD
    And Validaing the Thank you msg by chatbot
    Then closed the website
    Examples:
    
      | orderId    | email                	 	  	| postalCode |
	  	| 288855042  | ruthinnes@hotmail.co.uk 	  	| G76 0HS    |