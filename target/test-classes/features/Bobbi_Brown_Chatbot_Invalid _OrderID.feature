Feature: Track Order Status via Chatbot

  Scenario Outline: User tracks order using Invalid Order ID
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
    And the user enters invalid order ID "<invalid_orderId>"
		And the user should get invalid order Id reply from chatbot and another try
		And the user enters again invalid order Id "<invalid_orderId>"
		And the user should get sorry messege and redirect to advisor if avaliable
		Then closed the website
    Examples:
      | invalid_orderId    | 
      | 123AC415	 				 | 
	  
	  
	  
	 
      
      
      
