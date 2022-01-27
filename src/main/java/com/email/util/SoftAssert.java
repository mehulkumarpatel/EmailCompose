package com.email.util;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.email.driver.DriverProvider;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SoftAssert extends org.testng.asserts.SoftAssert {

	private String methodName = "";
	private String className = "";
	private String fullClassName = "";
	@SuppressWarnings("unused")
	private String fullMehtodName = "";
	private String dataDrivenID = "";
	private boolean isDataDriven = false;
	private static String prevMethod;
	private static int runCount = 0;
	private String directoryTime = new DateUtil().getDate();

	public SoftAssert(String methodName, boolean isDataDriven, String dataDrivenID) {
		if (prevMethod != null && prevMethod != "" && prevMethod.length() > 0) {
			if (prevMethod != methodName)
				runCount = 0;
		}
		runCount++;
		this.dataDrivenID = dataDrivenID;
		if (this.dataDrivenID == "") {
			this.dataDrivenID = "Run" + runCount;
		}

		this.isDataDriven = isDataDriven;
		this.fullMehtodName = methodName;
		this.methodName = methodName.length() > 20 ? methodName.substring(0, 20) : methodName;
		System.setProperty("ScreenshotDir", directoryTime);
	}

	public SoftAssert(String methodName, String className, boolean isDataDriven, String dataDrivenID) {
		if (prevMethod != null && prevMethod != "" && prevMethod.length() > 0) {
			if (prevMethod != methodName)
				runCount = 0;
		}
		runCount++;
		this.dataDrivenID = dataDrivenID;
		if (this.dataDrivenID == "") {
			this.dataDrivenID = "Run" + runCount;
		}

		this.isDataDriven = isDataDriven;
		this.fullMehtodName = methodName;
		this.fullClassName = className;
		this.methodName = methodName.length() > 20 ? methodName.substring(0, 20) : methodName;
		this.className = className.length() > 20 ? className.substring(0, 20) : className;
		System.setProperty("ScreenshotDir", directoryTime);
	}

	public SoftAssert(String methodName, String className) {
		this.fullMehtodName = methodName;
		this.fullClassName = className;
		this.methodName = methodName.length() > 20 ? methodName.substring(0, 20) : methodName;
		this.className = className.length() > 20 ? className.substring(0, 20) : className;
		System.setProperty("ScreenshotDir", directoryTime);

	}

	public SoftAssert(String methodName) {
		this.fullMehtodName = methodName;
		this.methodName = methodName.length() > 20 ? methodName.substring(0, 20) : methodName;
		System.setProperty("ScreenshotDir", directoryTime);
	}

	public SoftAssert() {
		this.methodName = "Assertion";
		System.setProperty("ScreenshotDir", directoryTime);
	}

	@Override
	public void assertTrue(boolean condition) {
		String methodName = this.methodName;
		takeScreenshot(methodName);
		super.assertTrue(condition);
	}

	@Override
	public void assertEquals(boolean actual, boolean expected) {
		String methodName = this.methodName;
		takeScreenshot(methodName);
		super.assertEquals(actual, expected);
	}

	@Override
	public void assertFalse(boolean condition) {
		String methodName = this.methodName;
		takeScreenshot(methodName);
		super.assertFalse(condition);
	}

	@Override
	public void assertAll() {
		super.assertAll();
		mergeScreeToPDF();
	}

	private String takeScreenshot(String methodName) {
		TakesScreenshot scrShot = ((TakesScreenshot) DriverProvider.driver);
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			String reportDir = new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator
					+ "test-output" + File.separator + "reports" + File.separator + directoryTime + "assertion";

			if (className == null || className.length() == 0)
				className = methodName;
			if (className != null)
				reportDir = reportDir + File.separator + className;
			if (isDataDriven)
				reportDir = reportDir + File.separator + dataDrivenID;
			System.setProperty("ReprotDirectory", reportDir);
			File destFile = new File(reportDir + File.separator + methodName + new DateUtil().getDate() + ".png");
			FileUtils.copyFile(srcFile, destFile);
			return destFile.getAbsolutePath();
		} catch (Exception e) {
		}
		return "";
	}

	private void mergeScreeToPDF() {

		String reportDir = new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator + "test-output"
				+ File.separator + "reports" + File.separator + directoryTime + "assertion";
		if (className == null || className.length() == 0)
			className = methodName;
		if (className != null)
			reportDir = reportDir + File.separator + className;
		if (isDataDriven)
			reportDir = reportDir + File.separator + dataDrivenID;

		try {
			File dir = new File(reportDir);
			File[] allFiles = dir.listFiles();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(new File(reportDir + File.separator + fullClassName + ".pdf")));
			writer.open();
			document.open();

			for (File file : allFiles) {
				if (file.isFile() && (file.getAbsolutePath().toLowerCase().contains(".png")
						|| file.getAbsolutePath().toLowerCase().contains(".jpg"))) {
					String fileName = file.getAbsolutePath();
					Image im = Image.getInstance(fileName);
					im.scaleToFit(((PageSize.A4.getWidth()) * 9) / 10, PageSize.A4.getHeight());
					document.add(im);
					document.add(new Paragraph(" "));
				}
			}
			document.close();
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		File srcFile = new File(reportDir + File.separator + fullClassName + ".pdf");
		String pdfFileDir = new File(System.getProperty("user.dir")).getAbsolutePath() + File.separator + "test-output"
				+ File.separator + "reports" + File.separator + directoryTime + "pdf";
		File destFile = new File(pdfFileDir + File.separator + fullClassName + ".pdf");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
		}

	}

}
