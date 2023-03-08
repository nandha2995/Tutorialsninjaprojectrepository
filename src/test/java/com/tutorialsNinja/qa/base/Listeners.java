package com.tutorialsNinja.qa.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsNinja.utilities.ExtentreportNG;

public class Listeners extends Base implements ITestListener {

	ExtentTest test;
	ExtentReports extent;
	ThreadLocal<ExtentTest> extentTest;
	
	
	@Override
	public void onStart(ITestContext context) {
		 extent = ExtentreportNG.generateExtentreport();
		 extentTest = new ThreadLocal<ExtentTest>();
	}

	
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().log(Status.PASS, "Test Passed");

	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().log(Status.FAIL, result.getThrowable());
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {

			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(captureScreenshot(result.getMethod().getMethodName(), driver),
				result.getMethod().getMethodName());

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().log(Status.SKIP, result.getThrowable());
	}

	
	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

	}

}
