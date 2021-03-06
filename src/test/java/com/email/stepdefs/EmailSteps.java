package com.email.stepdefs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.email.driver.DriverProvider;
import com.email.pages.EmailPage;
import com.email.pages.LoginPage;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmailSteps extends DriverProvider {
	private LoginPage loginPage;
	private EmailPage emailPage;
	@SuppressWarnings("unused")
	private String scenDesc;

	public EmailSteps() {
		this.loginPage = new LoginPage();
		this.emailPage = new EmailPage();
	}

	@Before
	public void before(Scenario scenario) {
		this.scenDesc = scenario.getName();
		setUpWebDriver();
	}

	@After
	public void after(Scenario scenario) {
		closeDriver(scenario);
	}

	@Given("^User navigate to email login page$")
	public void aUserNavigatesTologinPage() throws IOException {
		this.loginPage.navigateToLogin(softAssert);
	}

	@When("^User do login with username and password$")
	public void userDoLogin() throws Exception {
		this.loginPage.doLogin();
	}

	@Then("^User should see Email page$")
	public void verifyEmailPage() throws Exception {
		this.emailPage.verifyEmailPage();
	}

	@And("^User click on Compose button$")
	public void clickCompose() throws Exception {
		this.emailPage.clickCompose();
	}

	@Then("^User enters data in To text field$")
	public void enterToField() throws Exception {
		this.emailPage.enterToField();
	}

	@Then("^User enter email subject$")
	public void enterEmailSubject() throws Exception {
		this.emailPage.enterEmailSubject();
	}

	@Then("^User enter email subject \"([^\"]*)\" step$")
	public void enterEmailSubject(String subject) throws Exception {
		this.emailPage.enterEmailSubject(subject);
	}

	@Then("^User enter email body$")
	public void enterEmailBody() throws Exception {
		this.emailPage.enterEmailBody();
	}

	@Then("^User mark lable as Social$")
	public void markLabelAsSocial() throws Exception {
		this.emailPage.markLabelAsSocial();
	}

	@Then("^User click on Send button$")
	public void clickSend() throws Exception {
		this.emailPage.clickSend();
	}

	@Then("^User click on Social Tab$")
	public void clickSocialTab() throws Exception {
		this.emailPage.clickSocialTab();
	}

	@Then("^User open recieved email$")
	public void openReceivedEmail() throws Exception {
		this.emailPage.openReceivedEmail();
	}

	@Then("^User verify recieved email$")
	public void verifyRecievedEmail() throws Exception {
		this.emailPage.verifyRecievedEmail();
	}

	public static void main(String[] args) {
		String base = "Motorola Solutions";
		Map<String, Integer> countOfChar = new HashMap<>();

		for (int i = 0; i < base.length(); i++) {
			if (countOfChar.get(base.charAt(i) + "") != null) {
				countOfChar.put(base.charAt(i) + "", countOfChar.get(base.charAt(i) + "") + 1);
			} else {
				countOfChar.put(base.charAt(i) + "", 1);
			}
		}

		System.out.println("Count >" + countOfChar);
	}

}

//WhatsApp

//Application list android,ios
//Tools - Appium,Java,Sele,
//
//Windows,Mac
//Devices - OS list
//
//Application
//Pages
//Main
//Chat
//Settings
//Upload
//
//Framework :
//	
//Base
//	Driver 
//Util
//	UploadUtil
//	DataUtils
//	AppUtils
//	
//Pages extends Drivers
//Main
//Chat
//Settings
//Upload
//
//TestClass
//
//Resources
//	UserData
//	UploadData
//Maven
//TestNG
//prop
//Configs
	
	
	
	
	









