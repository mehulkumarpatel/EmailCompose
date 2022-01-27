package com.email.test;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.email.driver.DriverProvider;
import com.email.pages.EmailPage;
import com.email.pages.LoginPage;

public class ComposeEmail extends DriverProvider {
	private LoginPage loginPage;
	private EmailPage emailPage;
	@SuppressWarnings("unused")
	private String scenDesc;

	public ComposeEmail() {
		this.loginPage = new LoginPage();
		this.emailPage = new EmailPage();
	}

	@BeforeTest
	public void before(ITestContext scenario) {
		this.scenDesc = scenario.getName();
		setUpDriver();
	}

	@AfterTest
	public void after(ITestContext scenario) {
		closeDriver();
	}

	@Test
	public void aUserNavigatesTologinPage() throws IOException {
		this.loginPage.navigateToLogin();
		this.loginPage.doLogin();
		this.emailPage.verifyEmailPage();
		this.emailPage.clickCompose();
		this.emailPage.enterToField();
		this.emailPage.enterEmailSubject();
		this.emailPage.enterEmailSubject("Test Subject");
		this.emailPage.enterEmailBody();
		this.emailPage.markLabelAsSocial();
		this.emailPage.clickSend();
		this.emailPage.clickSocialTab();
		this.emailPage.openReceivedEmail();
		this.emailPage.verifyRecievedEmail();
	}
}