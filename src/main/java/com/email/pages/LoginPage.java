package com.email.pages;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.email.driver.DriverProvider;
import com.email.pageobjects.LoginPageLocators;
import com.email.util.SoftAssert;

public class LoginPage extends DriverProvider {

	private LoginPageLocators locator = new LoginPageLocators();

	public void navigateToLogin(SoftAssert softAssert) {
		getDriver().get(prop.getProperty("url"));
		waitForPageLoad();
		softAssert.assertTrue(true);
	}

	public void doLogin() {
		// Enter User Name read from the properties file
		WebElement userElement = getDriver().findElement(locator.usernameTextbox());
		String userName = new String(Base64.getDecoder().decode(prop.getProperty("username").getBytes()));
		userElement.sendKeys(userName);
		// Click next
		getDriver().findElement(locator.nextButton()).click();
		waitForPageLoad();
		// Enter Password read from the properties file
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.passwordBox()));
		WebElement passwordElement = getDriver().findElement(locator.passwordBox());
		String passwordValue = new String(Base64.getDecoder().decode(prop.getProperty("password").getBytes()));
		passwordElement.sendKeys(passwordValue);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.paswordNextButon()));
		getDriver().findElement(By.id("passwordNext")).click();
		waitForPageLoad();

		// handle Skip button if visible
		try {
			if (!getDriver().findElement(locator.composeButton()).isDisplayed()) {
				try {
					getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (getDriver().findElement(locator.updateButton()).isDisplayed()) {
						getDriver().findElement(locator.updateButton()).click();
					}
				} catch (Exception e) {
					getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")),
							TimeUnit.SECONDS);
				}
				try {
					getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (getDriver().findElement(locator.skipButton()).isDisplayed()) {
						getDriver().findElement(locator.skipButton()).click();
					}
				} catch (Exception e) {
					getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")),
							TimeUnit.SECONDS);
				}
			}

		} catch (Exception e) {
			getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		}

	}

	public static void main(String[] args) {
		String test = "mehulkumar200200";

		System.out.println(">" + new String(Base64.getEncoder().encode(test.getBytes())));
	}
}