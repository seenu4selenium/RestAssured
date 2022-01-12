package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WrapperClass extends BaseClass {

	// Constructor: By default constructor will run first method in a class.
	public WrapperClass() {

		File screenshotPath = new File(".\\screenshots");
		if (screenshotPath.exists()) {
			System.out.println("screenshot folder is exits***************");
		} else {
			screenshotPath.mkdir();
			System.out.println("screenshot folder is NOT available, system has created a Folder ***************");
		}
		System.out.println("**********************************************");

		File ExtentReportsPath = new File(".\\ExtentReports");
		if (ExtentReportsPath.exists()) {
			System.out.println("ExtentReports folder is exits***************");
		} else {
			ExtentReportsPath.mkdir();
			System.out.println("ExtentReports folder is NOT available, system has created a Folder ***************");
		}

		System.out.println("**********************************************");

		File FailedTestsScreenshotsPath = new File(".\\FailedTestsScreenshots");
		if (FailedTestsScreenshotsPath.exists()) {
			System.out.println("FailedTestsScreenshots folder is exits***************");
		} else {
			FailedTestsScreenshotsPath.mkdir();
			System.out.println(
					"FailedTestsScreenshots folder is NOT available, system has created a Folder ***************");
		}

	}

	public void chromeBrowserLaunch() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void firefoxBrowserLaunch() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public void edgeBrowserLaunch() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	public void operaBrowserLaunch() {
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
	}

	/*********
	 * Read Data from Properties file
	 * 
	 * @throws Exception
	 *********************/
	public void loaddata(String path) throws Exception {
		File file = new File(path);
		FileInputStream fileInput = new FileInputStream(file);
		prop.load(fileInput);
	}

	public String getdata(String key) {
		String keyvlaue = prop.getProperty(key);
		return keyvlaue;
	}

	/*******
	 * SendKeys *
	 * 
	 * @throws Exception
	 ************************/
	public void sendkeysByAnyLocator(By locator, String inputData) throws Exception {
		WebElement element = driver.findElement(locator);
		// isDisplay
		if (element.isDisplayed()) {
			// isEnabled
			if (element.isEnabled()) {
				highlightElement(element);
				element.clear();
				highlightElement(element);
				element.sendKeys(inputData);
			} else {
				System.out.println("Element is not enabled state, please check the locator*******");
			}
		} else {
			System.out.println("Element is not displayed, please check the locator*******");
		}

	}

	/*************** Click webelement By using Any Locator ******************/
	public void clickByAnyLocator(By locator) {
		WebElement ele = driver.findElement(locator);
		if (ele.isDisplayed()) {
			if (ele.isEnabled()) {
				ele.click();
			} else {
				System.out.println("The webelement is NOT enabled state on Screen, please check the locator");
			}
		} else {
			System.out.println("The webelement is NOT displayed on Screen, please check the locator");
		}
	}

	public void clickUsingJavaScript(By locator) throws Exception {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		highlightElement(element);
		executor.executeScript("arguments[0].click();", element);

	}

	public void highlightElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border='6px groove green'", element);
		Thread.sleep(1000);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.border=''", element);
	}

	/********** implicit wait ****************/
	public void implicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	/**************** Validate the error message ****************/
	public void validateTheErrorMessage(String expectedErrorMessage, By locator) {
		String actualErrorMessage = driver.findElement(locator).getText();
		if (expectedErrorMessage.equals(actualErrorMessage)) {
			System.out.println("Test Case is passed");
		} else {
			System.out.println("Test Case is failed");
		}
	}

	/****************** time stamp ********************/
	public String timeStamp() {
		// Timestamp
		Date d = new Date();
		System.out.println(d);
		DateFormat df = new SimpleDateFormat("yyyyMMMdd_HHmmss");
		String timeStamp = df.format(d);
		return timeStamp;
	}

	/******************
	 * Screenshot
	 * 
	 * @throws Exception
	 ********************/
	public void takeScreenshot() throws Exception {
		// Take current screen capture
		File abc = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Move taken screeshot to Specific folder
		FileHandler.copy(abc, new File(".\\screenshots\\TestCaseName" + timeStamp() + ".JPG"));

	}

	/******************
	 * Validate the element is present on DOM(Document Object Model) [Current
	 * Screen]
	 ******/
	public void validateWebelementPresent(By locator) {
		if (driver.findElements(locator).size() > 0) {
			System.out.println("The given locator has present on screen, the test case has passed");
		} else {
			System.out.println("The given locator has NOT present on screen, the test case has failed");
		}
	}

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {

		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();// 3
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		// elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 0
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;// 1
		}
		return elementpresenceCount;
	}

	/***************** get the element text by using any locator *****************/
	public String getTheElementTextUsingAnyLocator(By locator) {
		WebElement element = driver.findElement(locator);

		String eleText = driver.findElement(locator).getText();
		System.out.println(eleText);
		return eleText;
	}

	/************* get all the hyperlinks **************/

	public void getAllHyperLinksAndPrint() {
		List<WebElement> allHyperLinks = driver.findElements(By.tagName("a"));
		int linksCount = allHyperLinks.size();
		System.out.println(linksCount);// 80
		for (WebElement abc : allHyperLinks) {
			System.out.println(abc.getText());
		}

	}

	/** Verify Broken Links In a Web Page **************/
	public static void verifylink(String linkurl) {
		try {
//			100 Continue
//			300 Multiple Choices
//			404 Not Found
//			500 Internal Server Error
			URL url = new URL(linkurl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			System.out.println(connection.getResponseCode());
			if (connection.getResponseCode() == 100) {
				System.out.println(" The getResponseCode is : 100");
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == 200) {
				System.out.println(" The getResponseCode is : 200");
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == 300) {
				System.out.println(" The getResponseCode is : 300");
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == 400) {
				System.out.println(" The getResponseCode is : 400");
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == 500) {
				System.out.println(" The getResponseCode is : 500");
				System.out.println(linkurl + " - " + connection.getResponseMessage());
			}
			if (connection.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkurl + "-" + connection.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/****************** Dropdown selection **************************************/

	public void selectByVisibleText(By locater, String visibleText) {

		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByVisibleText(visibleText);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByIndex(By locater, int index) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByIndex(index);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectByValue(By locater, String value) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				dropdown.selectByValue(value);
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void printAllDropdownValues(By locater) {
		WebElement element = driver.findElement(locater);

		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (WebElement allvalue : dropdownValues) {
					System.out.println(allvalue.getText());
				}
			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}

	public void selectCustomiseOptionFromTheDropdownValues(By locater, String visibleText) {
		WebElement element = driver.findElement(locater);
		if (element.isDisplayed()) {
			// isEnabled()
			if (element.isEnabled()) {

				Select dropdown = new Select(element);
				List<WebElement> dropdownValues = dropdown.getOptions();
				// Print the size of dropdown values
				System.out.println(dropdownValues.size());
				// Print the dropdown values
				for (int i = 0; i < dropdownValues.size(); i++) {
					System.out.println(dropdownValues.get(i).getText());

					// Select Aug option from the dropdown
					if (dropdownValues.get(i).getText().equals(visibleText)) {
						dropdown.selectByIndex(i);
						break;
					}
				}

			} else {
				System.out.println("The webelement is NOT Enabled, please check**************");
			}
		} else {
			System.out.println("The webelement is NOT displayed, please check**************");
		}

	}


}
