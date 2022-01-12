package com.TestScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.*;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.objectrepository.Locaters;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utilities.WrapperClass;

public class ClassTemplate extends WrapperClass {

	// call CommonFunctions using object

	Locaters obj = new Locaters();

	// create property file class object
	Properties prop = new Properties();
	// Extents reports
	public static ExtentTest test;
	public static ExtentReports report;

	@Test
	public void login() throws InterruptedException, IOException {
		FileInputStream fi = new FileInputStream(".\\testData\\TestData.properties");
		prop.load(fi);

		// type URL
		driver.get(prop.getProperty("baseURL"));
		test.log(LogStatus.PASS, "Open URL", "Successfully Launched URL");

	}

	@BeforeClass
	public void beforeclass() throws Exception {
		// Extent Reports
		report = new ExtentReports(System.getProperty("user.dir") + "\\ExtentReports\\ExtentReportResults"
				+ System.currentTimeMillis() + ".html");
		test = report.startTest("ExtentDemo");

		chromeBrowserLaunch();
		test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "Test Passed");
	}

	@AfterMethod
	public void aftermethod(ITestResult ref) throws InterruptedException, Exception {

		takeScreenshotWithStatus(ref);

	}

	private void takeScreenshotWithStatus(ITestResult ref) {
		// TODO Auto-generated method stub
		
	}

	private String capture(WebDriver driver) throws Exception {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(".\\screenshots\\" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileHandler.copy(scrFile, Dest);
		return errflpath;
	}

	@AfterClass
	public void afterclass() throws InterruptedException {
		Thread.sleep(3000);
		report.endTest(test);
		report.flush();
		Thread.sleep(3000);
		driver.quit();
	}

}
