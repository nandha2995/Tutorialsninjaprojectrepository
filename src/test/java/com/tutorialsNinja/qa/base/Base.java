package com.tutorialsNinja.qa.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.tutorialsNinja.utilities.ReadConfig;

public class Base {

	WebDriver driver;
	ReadConfig readConfig = new ReadConfig();
	public String url = readConfig.getApplicationURL();
	public String testDataPath = readConfig.getTestDataPath();

	public WebDriver initializedriver() throws IOException {

		String browserName = readConfig.getBrowserName();
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(url);
		System.out.println(url);
		return driver;

	}

	public String captureScreenshot(String testCaseName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String screenshotpath = System.getProperty("user.dir") + "/Screenshots/" + testCaseName + ".png";
		try {
			FileHandler.copy(source, new File(screenshotpath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenshotpath;
	}

	public String randomstring() {
		Date date = new Date();
		String randomString = date.toString().replace(" ", "_").replace(":", "_") + "@gmail.com";
		return randomString;
	}
}
