package com.email.test;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.email.driver.DriverProvider;
import com.email.pages.EmailPage;
import com.email.pages.LoginPage;
import com.email.util.ExcelUtil;

public class ComposeEmailDataDriven extends DriverProvider {
	private LoginPage loginPage;
	private EmailPage emailPage;
	@SuppressWarnings("unused")
	private String scenDesc;

	public ComposeEmailDataDriven() {
		this.loginPage = new LoginPage();
		this.emailPage = new EmailPage();
	}

	@DataProvider()
	public static Object[][] applicationData() {
		return new ExcelUtil().readExcelData("Employee", "SubjectsData.xlsx");
	}

	@Test(dataProvider = "applicationData")
	public void aUserNavigatesTologinPage(String... strings) throws IOException {
		int i = 0;
		String name = strings[i++];
//		String email=strings[i++];

		this.loginPage.navigateToLogin(softAssert);
		this.loginPage.doLogin();
		this.emailPage.verifyEmailPage();
		this.emailPage.clickCompose();
		this.emailPage.enterToField();
		this.emailPage.enterEmailSubject();
		this.emailPage.enterEmailSubject(name + "Test Subject");
		this.emailPage.enterEmailBody();
		this.emailPage.markLabelAsSocial();
		this.emailPage.clickSend();
		this.emailPage.clickSocialTab();
		this.emailPage.openReceivedEmail();
		this.emailPage.verifyRecievedEmail();
	}
}