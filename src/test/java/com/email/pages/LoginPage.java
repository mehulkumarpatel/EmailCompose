package com.email.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.email.driver.DriverProvider;

public class LoginPage extends DriverProvider {

	public void navigateToLogin() {
		// Enter User Name read from the properties file
		WebElement userElement = driver.findElement(By.id("identifierId"));
		String userName = prop.getProperty("username");
		userElement.sendKeys(userName);

		// Click next
		driver.findElement(By.id("identifierNext")).click();

		// Enter Password read from the properties file
		By passwordElementIdentifier = By.name("password");
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordElementIdentifier));
		WebElement passwordElement = driver.findElement(passwordElementIdentifier);
		String passwordValue = prop.getProperty("password");
		passwordElement.sendKeys(passwordValue);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));
		driver.findElement(By.id("passwordNext")).click();
	}
	
	public void doLogin() {
		// Enter User Name read from the properties file
		WebElement userElement = driver.findElement(By.id("identifierId"));
		String userName = prop.getProperty("username");
		userElement.sendKeys(userName);

		// Click next
		driver.findElement(By.id("identifierNext")).click();

		// Enter Password read from the properties file
		By passwordElementIdentifier = By.name("password");
		wait.until(ExpectedConditions.presenceOfElementLocated(passwordElementIdentifier));
		WebElement passwordElement = driver.findElement(passwordElementIdentifier);
		String passwordValue = prop.getProperty("password");
		passwordElement.sendKeys(passwordValue);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("passwordNext")));
		driver.findElement(By.id("passwordNext")).click();
	}
}