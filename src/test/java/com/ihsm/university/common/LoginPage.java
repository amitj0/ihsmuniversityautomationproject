package com.ihsm.university.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.ihsm.university.base.BasePage;

;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	// locate the web elements

	@FindBy(name = "txtEmail")
	private WebElement username;
	@FindBy(name = "txtPassword")
	private WebElement password;
	@FindBy(xpath = "//button[@value='Log In']")
	private WebElement loginBtn;

	// methods to perform the actions

	public void enterUsrName(String Uname) {
		username.sendKeys(Uname);
	}

	public void enterPassword(String Upass) {
		password.sendKeys(Upass);
	}

	public void clickButton() {
		safeClick(loginBtn);
	}

	// login method
	public ChooseFacultyPage login(String name, String pass) {
		enterUsrName(name);
		enterPassword(pass);
		clickButton();

		return new ChooseFacultyPage(driver);
	}

}
