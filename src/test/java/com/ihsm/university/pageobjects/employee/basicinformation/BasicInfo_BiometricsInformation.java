package com.ihsm.university.pageobjects.employee.basicinformation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;
import com.ihsm.university.pageobjects.employee.designation.Designation_EmploymentRights;

public class BasicInfo_BiometricsInformation extends BasePage {

	public BasicInfo_BiometricsInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element here

	@FindBy(xpath = "//div[@id='btndivbionetric']//span")
	private WebElement addBiometricsInfoBtn;

	@FindBy(xpath = "//div[@Id='staticBackdropBiometricID']//input[@type='file']")
	private WebElement biometricInfoField;

	@FindBy(xpath = "//div[@id='staticBackdropBiometricID']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveBiometricsInfoBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement okButtonSuccessPopup;

	// methods to perform the action

	public void addBiometricsInfoBtn() {
		blinkElement(addBiometricsInfoBtn);
		safeClick(addBiometricsInfoBtn);
	}

	public void uploadBiometricFile(String filePath) {
		biometricInfoField.sendKeys(filePath);
	}

	public void saveBiometricsInfoBtn() {
		safeClick(saveBiometricsInfoBtn);
		try {
			captureScreenshot("Biometrics Information Filled");
		} catch (Exception e) {
			e.printStackTrace();
		}
		handleAlertIfPresent();
	}

	public void okButtonSuccessPopup() {
		blinkElement(okButtonSuccessPopup);
		handleModalOk(okButtonSuccessPopup);
	}
	
	public boolean isBiometricsInfoAdded() {
		return okButtonSuccessPopup.isDisplayed();
	}

	// fill biometrics info here
	public Designation_EmploymentRights fillBiometricsInfo(String filePath) {
		addBiometricsInfoBtn();
		uploadBiometricFile(filePath);
		saveBiometricsInfoBtn();
		okButtonSuccessPopup();
		
		return new Designation_EmploymentRights(driver);

	}

}
