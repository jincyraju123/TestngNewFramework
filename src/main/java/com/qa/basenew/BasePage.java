package com.qa.basenew;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;

	public BasePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void waitforelement(WebElement element) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitforElementtoVisible(WebElement element) {

		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void SwitchToFrame(WebElement element) {

		driver.switchTo().frame(element);

	}

	public void SwitchTodefaultcontent() {

		driver.switchTo().defaultContent();

	}

	public void gettitleofthepage() {

		System.out.println("Title of the current Page is : " + driver.getTitle());
	}

	public void actionclass(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	public void comparetext(String actualtext, String expectedtext) {

		Assert.assertEquals(actualtext, expectedtext);
	}

	public void alert() {

		driver.switchTo().alert().accept();
	}

}
