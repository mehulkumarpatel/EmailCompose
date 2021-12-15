package com.email.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.email.constants.CommonConstants;
import com.email.driver.DriverProvider;
import com.email.pageobjects.EmailPageLocators;

public class EmailPage extends DriverProvider {
	private EmailPageLocators locator = new EmailPageLocators();

	public void clickSend() {
		// Click Send
		driver.findElement(locator.sendButton()).click();
		waitForPageLoad();
		waitForTime(CommonConstants.SMALL_TIME);
	}

	public void markLabelAsSocial() {
		// Click More settings
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.moreOptionButton())).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.moreOptionLable())).click();

		// Choose Social label
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.bodySocialLabel())).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.applyButton())).click();
	}

	public void enterEmailBody() {
		// Enter email body
		String emailBody = prop.getProperty("email.body");
		driver.findElement(locator.emailBodyTextBox()).clear();
		driver.findElement(locator.emailBodyTextBox()).sendKeys(emailBody);
	}

	public void enterEmailSubject() {
		// emailSubject and emailbody to be used in this unit test.
		String emailSubject = prop.getProperty("email.subject");
		// Enter subject
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.subjectTextBox()));
		WebElement subjectBox = driver.findElement(locator.subjectTextBox());
		subjectBox.clear();
		subjectBox.sendKeys(emailSubject);
	}

	public void enterToField() {
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.toTextBox()));
		WebElement txtBoxToField = driver.findElement(locator.toTextBox());
		txtBoxToField.clear();
		String toUserValue = prop.getProperty("username");
		txtBoxToField.sendKeys(String.format("%s@gmail.com", toUserValue));
	}

	public void verifyEmailPage() {
		// Verify Compose
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.composeButton()));
		Assert.assertTrue(driver.findElement(locator.composeButton()).isDisplayed());
	}

	public void clickCompose() {
		// Click Compose
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.composeButton()));
		driver.findElement(locator.composeButton()).click();
	}

	public void clickSocialTab() {
		// Click Social Tab
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.socialTab())).click();
		waitForPageLoad();

		waitForTime(CommonConstants.SMALL_TIME);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.emailList()));
	}

	public void openReceivedEmail() {

		String emailSubject = prop.getProperty("email.subject");
		// Open the received email
		driver.findElement(locator.emailBySubject(emailSubject)).click();
		waitForTime(CommonConstants.SMALL_TIME);
	}

	public void verifyRecievedEmail() {
		// Verify the subject and body of the received email
		String subjectOfReceivedEmail = wait.until(ExpectedConditions.presenceOfElementLocated(locator.emailHeader()))
				.getText();

		String emailSubject = prop.getProperty("email.subject");
		Assert.assertEquals(emailSubject, subjectOfReceivedEmail);

		String bodyOfReceivedEmail = wait.until(ExpectedConditions.presenceOfElementLocated(locator.emailBody()))
				.getText();
		String emailBody = prop.getProperty("email.body");
		Assert.assertEquals(emailBody, bodyOfReceivedEmail);

	}
}