package com.email.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.email.driver.DriverProvider;
import com.email.pages.EmailPage;
import com.email.pages.LoginPage;
import com.email.util.SoftAssert;

public class ComposeEmail extends DriverProvider {
	private LoginPage loginPage;
	private EmailPage emailPage;

	public ComposeEmail() {
		this.loginPage = new LoginPage();
		this.emailPage = new EmailPage();
	}

	@Test
	public void aUserNavigatesTologinPage() throws IOException {
		softAssert = new SoftAssert(new Object() {
		}.getClass().getEnclosingMethod().getName(), new Object() {
		}.getClass().getSimpleName());

		this.loginPage.navigateToLogin(softAssert);
		this.loginPage.doLogin();
//		this.emailPage.verifyEmailPage();
//		this.emailPage.clickCompose();
//		this.emailPage.enterToField();
//		this.emailPage.enterEmailSubject();
//		this.emailPage.enterEmailSubject("Test Subject");
//		this.emailPage.enterEmailBody();
//		this.emailPage.markLabelAsSocial();
//		this.emailPage.clickSend();
//		this.emailPage.clickSocialTab();
//		this.emailPage.openReceivedEmail();
//		this.emailPage.verifyRecievedEmail();
		softAssert.assertAll();
	}
}