package com.email.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.email.constants.CommonConstants;
import com.email.util.SoftAssert;

import io.cucumber.core.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverProvider {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public static WebDriverWait wait = null;
	public SessionId session = null;
	public static Properties prop = new Properties();
	protected SoftAssert softAssert;
	public DriverProvider() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM ChromegetDriver().exe");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			prop.load(new FileInputStream("./src/test/resources/config/test.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void before(ITestContext scenario) {
		setUpWebDriver();
	}

	@AfterTest
	public void after(ITestContext scenario) {
		closeDriver();
	}

	public WebDriver getDriver() {
		return DriverProvider.driver.get();
	}

	public void setDriver(WebDriver driver) {
		if (DriverProvider.driver.get() == null)
			DriverProvider.driver.set(driver);
	}

	public void setUpWebDriver() {
		String browser = prop.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}
		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webgetDriver().chrome.driver",
			// prop.getProperty("webgetDriver().chrome.driver"));
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("start-maximized");
			chromeOptions.addArguments("enable-automation");

			// session = ((ChromeDriver)driver).getSessionId();
			setDriver(new ChromeDriver(chromeOptions));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webgetDriver().gecko.driver",
			// prop.getProperty("webgetDriver().gecko.driver"));
			setDriver(new FirefoxDriver());
			getDriver().manage().window().maximize();
			// session = ((FirefoxDriver)driver).getSessionId();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			setDriver(new EdgeDriver());
			getDriver().manage().window().maximize();
			// session = ((FirefoxDriver)driver).getSessionId();
			break;
		default:
			throw new IllegalArgumentException("Browser \"" + browser + "\" isn't supported.");

		}
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		wait = new WebDriverWait(getDriver(), Integer.parseInt(prop.getProperty("timeout")));

	}

	public void closeDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			saveScreenshotsForScenario(scenario);
		}
		getDriver().close();
		getDriver().quit();
	}

	public void closeDriver() {
		getDriver().close();
		getDriver().quit();
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

	public boolean isDisplayed(By by, long timeputms) {
		getDriver().manage().timeouts().implicitlyWait(timeputms, TimeUnit.MILLISECONDS);

		boolean isDisplayed = false;
		try {
			isDisplayed = getDriver().findElement(by).isDisplayed();
		} catch (Exception e) {
		}
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);
		return isDisplayed;
	}

	public boolean isDisplayed(By by) {
		return isDisplayed(by, CommonConstants.LARGE_TIME_MS);

	}

	public void waitForNotVisible(By by, long timeoutms) {
		getDriver().manage().timeouts().implicitlyWait(CommonConstants.SMALL_TIME_MS, TimeUnit.MILLISECONDS);

		while (isDisplayed(by) && timeoutms > 0) {
			timeoutms = timeoutms - 1000;
			waitForTime(1000);
		}
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("timeout")), TimeUnit.SECONDS);

	}

	public void waitForTime(String timemilisec) {
		try {
			Thread.sleep(Integer.parseInt(timemilisec));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void waitForTime(long timemilisec) {
		try {
			Thread.sleep(timemilisec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
