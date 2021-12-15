package com.email.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.core.api.Scenario;

public class DriverProvider {

	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public SessionId session = null;
	public static Properties prop = new Properties();

	public DriverProvider() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			prop.load(new FileInputStream("./src/test/resources/config/test.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WebDriver getDriver() {
		return this.getDriver();
	}

	public void setDriver(WebDriver driver) {
		DriverProvider.driver = driver;
	}

	public void setUpDriver() {
		String browser = prop.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", prop.getProperty("webdriver.chrome.driver"));
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("enable-automation");

			// session = ((ChromeDriver)driver).getSessionId();
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", prop.getProperty("webdriver.gecko.driver"));
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			// session = ((FirefoxDriver)driver).getSessionId();
			break;
		default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");

		}
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Integer.parseInt(prop.getProperty("timeout")));

	}

	public void closeDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			saveScreenshotsForScenario(scenario);
		}
		driver.close();
		driver.quit();
	}

	private void saveScreenshotsForScenario(final Scenario scenario) {
		final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screenshot, "image/png");
	}

	public void waitForPageLoad() {
		try {
			ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
		} catch (Exception e) {
		}

	}

	public void waitForTime(String timemilisec) {
		try {
			Thread.sleep(Integer.parseInt(timemilisec));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
