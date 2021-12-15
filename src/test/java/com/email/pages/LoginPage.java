package com.email.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.email.driver.DriverProvider;

public class LoginPage extends DriverProvider {

	public void navigateToLogin() {

		driver.get("https://mail.google.com/");
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
		try {
			if (!driver.findElement(By.xpath("//*[@role='button' and (.)='Compose']")).isDisplayed()) {
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (driver.findElement(By.xpath("//*[text()='Update']")).isDisplayed()) {
						driver.findElement(By.xpath("//*[text()='Update']")).click();
					}
				} catch (Exception e) {
					driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")),
							TimeUnit.SECONDS);
				}
				try {
					driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					if (driver.findElement(By.xpath("//*[text()='skip']")).isDisplayed()) {
						driver.findElement(By.xpath("//*[text()='skip']")).click();
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