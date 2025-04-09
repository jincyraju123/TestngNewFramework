package com.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.basenew.BasePage;

public class LoginPage extends BasePage {
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {

		super(driver);
		this.driver = driver;

 	}

	@FindBy(id = "userEmail")
	WebElement username;
	@FindBy(id = "userPassword")
	WebElement password;
	@FindBy(id = "login")
	WebElement loginButton;

	public void loginApplication(String userName, String passWord) {

		username.sendKeys(userName);
		password.sendKeys(passWord);
		loginButton.click();
	}
	
}
