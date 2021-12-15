package com.email.pageobjects;

import org.openqa.selenium.By;

public class EmailPageLocators {

	public By sendButton() {
		return By.xpath("//*[@role='button' and text()='Send']");
	}

	public By moreOptionButton() {
		return By.xpath("//div[@class='J-JN-M-I J-J5-Ji Xv L3 T-I-ax7 T-I']/div[2]");
	}

	public By moreOptionLable() {
		return By.xpath("//span[@class='J-Ph-hFsbo']");
	}

	public By bodySocialLabel() {
		return By.xpath("//div[@class='J-LC-Jz' and text()='Social']/div[@class='J-LC-Jo J-J5-Ji']");
	}

	public By applyButton() {
		return By.xpath("//div[@class='J-JK-Jz' and text()='Apply']");
	}

	public By emailBodyTextBox() {
		return By.xpath("//div[@class='Am Al editable LW-avf tS-tW']");
	}

	public By subjectTextBox() {
		return By.name("subjectbox");
	}

	public By toTextBox() {
		return By.name("to");
	}

	public By composeButton() {
		return By.xpath("//*[@role='button' and (.)='Compose']");
	}

	public By socialTab() {
		return By.xpath("//div[@class='aKz' and text()='Social']");
	}

	public By emailList() {
		return By.xpath("//table[@class='F cf zt']/tbody/tr[1]/td[3]/span");
	}

	public By emailBySubject(String emailSubject) {
		return By.xpath("//tr[@class='zA zE']//div[@class='xS']//span[@class='bqe'][text()='" + emailSubject + "']");
	}

	public By emailHeader() {
		return By.xpath("//h2[@class='hP']");
	}

	public By emailBody() {
		return By.xpath("//div[@class='a3s aiL ']/div[1]");
	}

}
