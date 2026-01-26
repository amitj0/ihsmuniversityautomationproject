package com.ihsm.university.pageobjects.student.basicinformation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_GeneralInformation_Prerights extends BasePage {

	public BasicInfo_GeneralInformation_Prerights(WebDriver driver) {
		super(driver);
	}
	
	// locate the web element here
	@FindBy(xpath = "//span[@data-bs-target='#GeneralInfoId']")
	private WebElement addGeneralInfo;
	
	@FindBy(xpath = "//a[@href='#tab21']")
	private WebElement preferRightsBtn;
	
	@FindBy(xpath = "//div[@id='tab21']//ng-select")
	private WebElement preferRightField;
	
	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> preferRightFieldList;
	
	
	@FindBy(xpath = "//div[@id='tab21']//input[@type='file']")
	private WebElement uploadField;
	
	@FindBy(xpath = "//div[@id='tab21']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkBtn;
	
	// methods to perform the action
	public void addGeneralInfoTab() {
		safeClick(addGeneralInfo);
	}
	public void preferRightsBtn() {
		blinkElement(preferRightsBtn);
		safeClick(preferRightsBtn);
	}
	public void preferRightField() {
		safeClick(preferRightField);
	}
	public void preferRightFieldList(String preferRight) {
		safeClick(preferRightField);
		
		for (WebElement option : preferRightFieldList) {
			if (option.getText().trim().equalsIgnoreCase(preferRight)) {
				safeClick(option);
				return;
			}
		}
	}
	public void uploadField(String filePath) {
		uploadField.sendKeys(filePath);
	}
	public void saveBtn() {
		blinkElement(saveBtn);
		try {
			captureScreenshot("General Pre Information Saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
		safeClick(saveBtn);
		handleAlertIfPresent();
	}
	public void saveOkBtn() {
		blinkElement(saveOkBtn);
		safeClick(saveOkBtn);
	}
	
	// fill prefer rights information
	public BasicInfo_GeneralInformation_SocialStatus fillPreferRightsInformation(String preferRight, String filePath) {
		addGeneralInfoTab();
		preferRightField();
		preferRightFieldList(preferRight);
		uploadField(filePath);
		saveBtn();
		saveOkBtn();
		
		return new BasicInfo_GeneralInformation_SocialStatus(driver);
	}
	
	

}
