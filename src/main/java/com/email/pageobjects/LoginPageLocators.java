package com.email.pageobjects;

import org.openqa.selenium.By;

public class LoginPageLocators {

	public By usernameTextbox() {
		return By.id("identifierId");
	}

	public By nextButton() {
		return By.id("identifierNext");
	}

	public By passwordBox() {
		return By.name("password");
	}

	public By paswordNextButon() {
		return By.id("passwordNext");
	}

	public By composeButton() {
		return By.xpath("//*[@role='button' and (.)='Compose']");
	}

	public By updateButton() {
		return By.xpath("//*[text()='UPDATE']");
	}

	public By skipButton() {
		return By.xpath("//*[text()='skip']");
	}

}
