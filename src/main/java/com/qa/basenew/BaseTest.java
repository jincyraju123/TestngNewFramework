package com.qa.basenew;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public  WebDriver driver;
	public  Properties prop;

	// reading url and browser from properties file

	public WebDriver setUp() throws IOException {

		prop = new Properties();
		//File file = new File("config.properties");
		FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");
		// String url = prop.getProperty("url");
		

		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		} else {
			System.out.println("Provide a valid browser name");
		}

		driver.manage().window().maximize();
		// driver.get(url);

		return driver;

	}

	public void launchApplication() throws Exception {

		driver.get("https://rahulshettyacademy.com/client");

	}

	public String getScreenshot(String testCaseName, WebDriver driver) {

		TakesScreenshot screenshot = ((TakesScreenshot) driver);
		File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		try {
			FileUtils.copyFile(srcfile, file);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	public void close() {
		if (driver != null) {
			driver.quit();
		}
	}
}
