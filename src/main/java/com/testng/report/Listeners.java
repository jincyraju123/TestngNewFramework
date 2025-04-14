package com.testng.report;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.basenew.BaseTest;


public class Listeners extends BaseTest implements ITestListener, IRetryAnalyzer {

	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe using ThreadLocal class
	ExtentTest test;
	ExtentReports extent = ExtentReportTestNG.getReportObject();

	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id ---->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		// test.log(Status.PASS, "Test Passed");
		extentTest.get().log(Status.PASS, "Test Passed"); // thread safe
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// test.log(Status.FAIL, "Test Failed");
		// test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable()); // thread

		try {

			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());

		} catch (Exception e) {

			e.printStackTrace();
		}

		String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		// test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName()); // thread

	}

	@Override
	public void onTestSkipped(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {

		extent.flush();

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public boolean retry(ITestResult result) {

		int retryCount = 0;
		int maxCount = 3;
		
		if (retryCount < maxCount) {
			retryCount++;
			return true;
		}

		return false;
	}

}
