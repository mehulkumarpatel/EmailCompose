package com.email.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.email.driver.DriverProvider;

public class EmailPage extends DriverProvider {

	public void clickSend() {
		// Click Send
		driver.findElement(By.xpath("//*[@role='button' and text()='Send']")).click();
	}

	public void markLabelAsSocial() {
		// Click More settings
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@class='J-JN-M-I J-J5-Ji Xv L3 T-I-ax7 T-I']/div[2]")))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='J-Ph-hFsbo']"))).click();

		// Choose Social label
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@class='J-LC-Jz' and text()='Social']/div[@class='J-LC-Jo J-J5-Ji']"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='J-JK-Jz' and text()='Apply']")))
				.click();
	}

	public void enterEmailBody() {
		// Enter email body
		String emailBody = prop.getProperty("email.body");
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).clear();
		driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys(emailBody);
	}

	public void enterEmailSubject() {
		// emailSubject and emailbody to be used in this unit test.
		String emailSubject = prop.getProperty("email.subject");
		// Enter subject
		By subjectBoxIdentifier = By.name("subjectbox");
		wait.until(ExpectedConditions.presenceOfElementLocated(subjectBoxIdentifier));
		WebElement subjectBox = driver.findElement(subjectBoxIdentifier);
		subjectBox.clear();
		subjectBox.sendKeys(emailSubject);
	}

	public void enterToField() {
		By toFieldIdentifier = By.name("to");
		wait.until(ExpectedConditions.presenceOfElementLocated(toFieldIdentifier));
		WebElement txtBoxToField = driver.findElement(toFieldIdentifier);
		txtBoxToField.clear();
		String toUserValue = prop.getProperty("username");
		txtBoxToField.sendKeys(String.format("%s@gmail.com", toUserValue));
	}

	public void verifyEmailPage() {
		// Verify Compose
		By composeElementIdentifier = By.xpath("//*[@role='button' and (.)='Compose']");
		wait.until(ExpectedConditions.presenceOfElementLocated(composeElementIdentifier));
		Assert.assertTrue(driver.findElement(By.xpath("//*[@role='button' and (.)='Compose']")).isDisplayed());
	}

	public void clickCompose() {
		// Click Compose
		By composeElementIdentifier = By.xpath("//*[@role='button' and (.)='Compose']");
		wait.until(ExpectedConditions.presenceOfElementLocated(composeElementIdentifier));
		driver.findElement(composeElementIdentifier).click();
	}

	public void clickSocialTab() {
		// Click Social Tab
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='aKz' and text()='Social']")))
				.click();

		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//table[@class='F cf zt']/tbody/tr[1]/td[3]/span")));

		try {
			driver.findElement(By.xpath("//table[@class='F cf zt']/tbody/tr[1]/td[3]/span")).click();
		} catch (ElementNotVisibleException e) {
			driver.findElements(By.xpath("//table[@class='F cf zt']/tbody/tr[1]/td[3]/span")).get(1).click();
		}
	}

	public void openReceivedEmail() {
		// Open the received email
		try {
			driver.findElement(By.xpath("//table[@class='F cf zt']/tbody/tr[1]")).click();
		} catch (ElementNotVisibleException e) {
			driver.findElements(By.xpath("//table[@class='F cf zt']/tbody/tr[1]")).get(1).click();
		}

	}

	public void verifyRecievedEmail() {
		// Verify email came under proper Label i.e. "Social"
		try {
			driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji T-I-Js-Gs mA mw T-I-ax7 L3']")).click();
		} catch (ElementNotVisibleException e) {
			driver.findElements(By.xpath("//div[@class='T-I J-J5-Ji T-I-Js-Gs mA mw T-I-ax7 L3']")).get(1).click();
		}

		String isSocialLabelChecked = driver.findElement(By.xpath("//div[@class='J-LC J-Ks-KO J-LC-JR-Jp']"))
				.getAttribute("aria-checked");
		Assert.assertEquals("true", isSocialLabelChecked);

		// Verify the subject and body of the received email
		String subjectOfReceivedEmail = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='hP']"))).getText();

		String emailSubject = prop.getProperty("email.subject");
		Assert.assertEquals(emailSubject, subjectOfReceivedEmail);

		String bodyOfReceivedEmail = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='a3s aXjCH ']"))).getText();
		String emailBody = prop.getProperty("email.body");
		Assert.assertEquals(emailBody, bodyOfReceivedEmail);

	}
}