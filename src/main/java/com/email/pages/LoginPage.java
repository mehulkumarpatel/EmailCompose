package com.email.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.email.driver.DriverProvider;
import com.email.pageobjects.LoginPageLocators;

public class LoginPage extends DriverProvider {

	private LoginPageLocators locator = new LoginPageLocators();

	public void navigateToLogin() {
		driver.get(prop.getProperty("url"));
		waitForPageLoad();
	}

	public void doLogin() {
		// Enter User Name read from the properties file
		WebElement userElement = driver.findElement(locator.usernameTextbox());
		String userName = prop.getProperty("username");
		userElement.sendKeys(userName);

		// Click next
		driver.findElement(locator.nextButton()).click();
		waitForPageLoad();
		// Enter Password read from the properties file
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.passwordBox()));
		WebElement passwordElement = driver.findElement(locator.passwordBox());
		String passwordValue = prop.getProperty("password");
		passwordElement.sendKeys(passwordValue);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator.paswordNextButon()));
		driver.findElement(By.id("passwordNext")).click();
		waitForPageLoad();

		// handle Skip button if visible
		try {
			if (!driver.findElement(locator.composeButton()).isDisplayed()) {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (driver.findElement(locator.updateButton()).isDisplayed()) {
						driver.findElement(locator.updateButton()).click();
					}
				} catch (Exception e) {
					driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")),
							TimeUnit.SECONDS);
				}
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (driver.findElement(locator.skipButton()).isDisplayed()) {
						driver.findElement(locator.skipButton()).click();
					}
				} catch (Exception e) {
					driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")),
							TimeUnit.SECONDS);
				}
			}

		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		}

	}
}