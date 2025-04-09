package com.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.basenew.BaseTest;
import com.qa.pageobjects.LoginPage;

public class LoginTest extends BaseTest {

	WebDriver driver;
	LoginPage loginPage;

	@BeforeMethod

	public void beforeMethod() throws Exception {
		
		driver=setUp();     
        launchApplication();
		loginPage = new LoginPage(driver);

	}

	@Test
	public void loginWithValidCredentials() {
		
		loginPage.loginApplication("rajujincy@gmail.com", "Test123#");
	}
	
	@AfterMethod
	
	public void tearDown() {
		
		close();
	}

}
