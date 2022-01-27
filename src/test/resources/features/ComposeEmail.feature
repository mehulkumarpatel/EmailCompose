Feature: Email Compose



  @compose_email
  Scenario: Check Email Compose feature
  	Given User navigate to email login page
  	When User do login with username and password
		Then User should see Email page
		And User click on Compose button
		And User enters data in To text field
		And User enter email subject "new step" step
		And User enter email body
		And User mark lable as Social
		And User click on Send button
		And User click on Social Tab
		And User open recieved email
		Then User verify recieved email 