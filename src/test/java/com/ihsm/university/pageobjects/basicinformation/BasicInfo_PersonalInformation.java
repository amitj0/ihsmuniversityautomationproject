package com.ihsm.university.pageobjects.basicinformation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_PersonalInformation extends BasePage {

	public BasicInfo_PersonalInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element here
	@FindBy(xpath = "//span[@data-bs-target='#basicInfoId']")
	private WebElement addPersonalInfoBtn;
	
	@FindBy(xpath = "//div[@id='basicInfoId']//input[@name='FIRST_NAME_IN_RUSSIAN']")
	private WebElement firstNameRussianLangField;
	
	@FindBy(xpath = "//div[@id='basicInfoId']//input[@name='LAST_NAME_IN_RUSSIAN']")
	private WebElement lastNameRussianLangField;
	
	@FindBy(xpath = "//div[@id='basicInfoId']//input[@name='CITY']")
	private WebElement cityField;
	
	@FindBy(xpath = "//div[@id='basicInfoId']//ng-select[@name='marital']")
	private WebElement maritalStatusField;
	
	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> maritalStatusFieldList;
	
	
	@FindBy(xpath = "//div[@id='basicInfoId']//textarea[@name='ADDRESS']")
	private WebElement addressField;
	
	@FindBy(xpath = "//div[@id='basicInfoId']//ancestor::div[contains(@class,'modal-content')]//button[normalize-space(text())='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']") 
	private WebElement alertOkBtn;
	
	// methods to perform the action
	
	public void addPersonalInfoBtn() {
		blinkElement(addPersonalInfoBtn);
		safeClick(addPersonalInfoBtn);
	}
	
	public void firstNameRussianLangField(String firstNameRussian) {
		safeClick(firstNameRussianLangField);
		firstNameRussianLangField.sendKeys(firstNameRussian);
	}
	public void lastNameRussianLangField(String lastNameRussian) {
		lastNameRussianLangField.sendKeys(lastNameRussian);
	}
	public void cityField(String city) {
		cityField.sendKeys(city);
	}
	
	public void maritalStatusFieldList(String maritalStatus) {
		safeClick(maritalStatusField);
		
		for (WebElement option : maritalStatusFieldList) {
			if (option.getText().trim().equalsIgnoreCase(maritalStatus)) {
				safeClick(option);
				return;
			}
		}
	}
	public void addressField(String address) {
		addressField.clear();
		addressField.sendKeys(address);
	}
	public void saveBtn() {
		blinkElement(saveBtn);
		try {
			captureScreenshot("Personal InfoFormation Filled");
		} catch (IOException e) {
			e.printStackTrace();
		}
		safeClick(saveBtn);
		handleAlertIfPresent();
	}
	public void alertOkBtn() {
		blinkElement(alertOkBtn);
		handleModalOk(alertOkBtn);
	}
	
	// fill the personal information form
	public BasicInfo_Biometrics fillPersonalInformationForm(String firstNameRussian, String lastNameRussian, String city, String maritalStatus, String address) {
		addPersonalInfoBtn();
		firstNameRussianLangField(firstNameRussian);
		lastNameRussianLangField(lastNameRussian);
		cityField(city);
		maritalStatusFieldList(maritalStatus);
		addressField(address);
		saveBtn();
		alertOkBtn();
		return new BasicInfo_Biometrics(driver);
	}
	

}
