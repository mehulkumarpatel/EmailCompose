Feature: Email Compose

  @compose_email
  Scenario: Check Email Compose feature
  	Given User navigate to email login page
  	When User do login with username and password
		Then User should see Email page
		Then User click on Compose button
		Then User enters data in To text field
		Then User enter email subject
		Then User enter email subject
		Then User mark lable as Social
		Then User click on Send button