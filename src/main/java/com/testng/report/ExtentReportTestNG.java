package com.testng.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportTestNG {
	
	public static ExtentReports getReportObject() {
		
		String path=System.getProperty("user.dir")+"/src/test/resources/ExtentReports/ExtentReport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Results");
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setTheme(Theme.DARK);
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Jincy");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		return extent;
	}

}
