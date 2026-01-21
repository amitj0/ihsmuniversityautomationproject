package com.ihsm.university.pageobjects.basicinformation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_FamilyInformation extends BasePage {

	public BasicInfo_FamilyInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element here

	@FindBy(xpath = "//span[@data-bs-target='#familyinfoid']")
	private WebElement addFamilyBtn;

	@FindBy(xpath = "//div[@id='familyinfoid']//ng-select[@name='GUARDIAN']")
	private WebElement guardianField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> guardianFieldList;

	@FindBy(xpath = "//div[@id='familyinfoid']//input[@name='FULLNAME']")
	private WebElement fullNameField;

	@FindBy(xpath = "//div[@id='familyinfoid']//input[@name='DOB']")
	private WebElement dobField;

	@FindBy(xpath = "//div[@id='familyinfoid']//label[normalize-space()='Occupation']/following-sibling::div[@class='input-group']")
	private WebElement occupationField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> occupationFieldList;

	@FindBy(xpath = "(//div[@id='familyinfoid']//div[contains(@class,'ng-select-container')])[4]")
	private WebElement mobileNoCountryCodeField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> mobileNoCountryCodeFieldList;

	@FindBy(xpath = "//input[@name='MOBILENO']")
	private WebElement mobileNoField;

	@FindBy(xpath = "//div[@id='familyinfoid']//label[normalize-space()='Disability']/following-sibling::div[@class='input-group']")
	private WebElement disabilityField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> disabilityFieldList;

	@FindBy(xpath = "//div[@id='familyinfoid']//label[normalize-space()='COUNTRY']/following-sibling::div[@class='input-group']")
	private WebElement countryField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> countryFieldList;

	@FindBy(xpath = "//div[@id='familyinfoid']//label[normalize-space()='State']/following-sibling::div[@class='input-group']")
	private WebElement stateField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> stateFieldList;

	@FindBy(xpath = "//div[@id='familyinfoid']//label[normalize-space()='City']/following-sibling::div[@class='input-group']")
	private WebElement cityField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> cityFieldList;

	@FindBy(xpath = "//div[@id='familyinfoid']//textarea[@placeholder='Address']")
	private WebElement addressField;

	@FindBy(xpath = "//div[@id='familyinfoid']//ancestor::div[contains(@class,'modal-content')]//button[normalize-space(text())='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkBtn;

	// methods to perform the action

	public void addFamilyBtn() {
		blinkElement(addFamilyBtn);
		safeClick(addFamilyBtn);
	}

	public void guardianField() {
		safeClick(guardianField);
	}

	public void guardianFieldList(String guardianType) {
		safeClick(guardianField);

		for (WebElement option : guardianFieldList) {
			if (option.getText().trim().equalsIgnoreCase(guardianType)) {
				safeClick(option);
				return;
			}
		}
	}

	public void enterFullName(String fullName) {
		safeClick(fullNameField);
		fullNameField.sendKeys(fullName);
	}

	public void enterDob(String dob) {
		safeClick(dobField);
		dobField.sendKeys(dob);
	}

	public void occupationFieldList(String occupationType) {
		safeClick(occupationField);

		for (WebElement option : occupationFieldList) {
			if (option.getText().trim().equalsIgnoreCase(occupationType)) {
				safeClick(option);
				return;
			}
		}
	}

	public void mobileNoCountryCodeFieldList(String countryCode) {
		safeClick(mobileNoCountryCodeField);

		for (WebElement option : mobileNoCountryCodeFieldList) {
			if (option.getText().trim().equalsIgnoreCase(countryCode)) {
				safeClick(option);
				return;
			}
		}
	}

	public void enterMobileNo(String mobileNo) {
		safeClick(mobileNoField);
		mobileNoField.sendKeys(mobileNo);
	}

	public void disabilityFieldList(String disabilityType) {
		safeClick(disabilityField);

		for (WebElement option : disabilityFieldList) {
			if (option.getText().trim().equalsIgnoreCase(disabilityType)) {
				safeClick(option);
				return;
			}
		}
	}

	public void countryFieldList(String countryName) {
		safeClick(countryField);

		for (WebElement option : countryFieldList) {
			if (option.getText().trim().equalsIgnoreCase(countryName)) {
				safeClick(option);
				return;
			}
		}
	}

	public void stateFieldList(String stateName) {
		safeClick(stateField);

		for (WebElement option : stateFieldList) {
			if (option.getText().trim().equalsIgnoreCase(stateName)) {
				safeClick(option);
				return;
			}
		}
	}

	public void cityFieldList(String cityName) {
		safeClick(cityField);

		for (WebElement option : cityFieldList) {
			if (option.getText().trim().equalsIgnoreCase(cityName)) {
				safeClick(option);
				return;
			}
		}
	}

	public void enterAddress(String address) {
		safeClick(addressField);
		addressField.sendKeys(address);
	}

	public void saveBtn() {
		blinkElement(saveBtn);
		try {
			captureScreenshot("Family Information Details");
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

	// fill the Family Information
	public BasicInfo_LanguageInformation fillFamilyInformation(String guardianType, String fullName, String dob,
			String occupationType, String mobCountryCode, String mobileNo, String disabilityType, String countryName,
			String stateName, String cityName, String address) {
		addFamilyBtn();
		guardianField();
		guardianFieldList(guardianType);
		enterFullName(fullName);
		enterDob(dob);
		occupationFieldList(occupationType);
		mobileNoCountryCodeFieldList(mobCountryCode);
		enterMobileNo(mobileNo);
		disabilityFieldList(disabilityType);
		countryFieldList(countryName);
		stateFieldList(stateName);
		cityFieldList(cityName);
		enterAddress(address);
		saveBtn();
		saveOkBtn();

		return new BasicInfo_LanguageInformation(driver);
	}

}
