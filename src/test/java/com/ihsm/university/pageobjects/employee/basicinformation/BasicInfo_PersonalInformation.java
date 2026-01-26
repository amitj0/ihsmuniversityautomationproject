package com.ihsm.university.pageobjects.employee.basicinformation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_PersonalInformation extends BasePage {

	public BasicInfo_PersonalInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element
	@FindBy(xpath = "//div[@id='divbtnemppersonalinfo']//span")
	private WebElement addPersonalInfoBtn;

	@FindBy(name = "strMiddleName")
	private WebElement nameInRussianLangField;

	@FindBy(name = "strFirstName")
	private WebElement nameInEnglishField;

	@FindBy(name = "strTinNo")
	private WebElement tinNumberField;

	@FindBy(name = "strWONumbers")
	private WebElement workOrderNumberField;

	@FindBy(xpath = "//div[@id='divPersonalInfoMaodel']//input[@type='date' and @name='DOB']")
	private WebElement dobFieldPersonalInfo;

	@FindBy(xpath = "//div[@id='divPersonalInfoMaodel']//ng-select[@name='GENDER']")
	private WebElement genderDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> genderDropdownOptions;

	@FindBy(name = "strMaritialStatus")
	private WebElement maritalStatusDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> maritalStatusDropdownOptions;

	@FindBy(xpath = "(//div[@id='divPersonalInfoMaodel']//span[@data-bs-target='#addvalue'])[1]")
	private WebElement addMaritalStatusPlusButton;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//input[@name='strMasterName']")
	private WebElement maritalStatusInputField;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveMaritalStatusButton;

	@FindBy(name = "dttDateOfJoining")
	private WebElement dateOfJoiningField;

	@FindBy(xpath = "//div[@id='divPersonalInfoMaodel']//input[@name='EMAIL']")
	private WebElement emailFieldPersonalInfo;

	@FindBy(xpath = "//label[normalize-space()='Mobile No*']/following::ng-select[1]//div[contains(@class,'ng-select-container')]")
	private WebElement selectCountryCodeField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> selectCountryCodeOptions;

	@FindBy(xpath = "//div[@id='divPersonalInfoMaodel']//input[@name='MOBILE1']")
	private WebElement mobileNumberField;

	@FindBy(name = "strAddress")
	private WebElement addressField;

	@FindBy(name = "strSecondAddress")
	private WebElement addressLine2Field;

	@FindBy(xpath = "//div[@id='divPersonalInfoMaodel']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveButtonPersonalInfo;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkButtonPersonalInfo;

	// methods to perform the action

	public void addPersonalInfoBtn() {
		blinkElement(addPersonalInfoBtn);
		safeClick(addPersonalInfoBtn);
	}

	public void nameInRussianLangField(String nameInRussianLang) {
		safeClick(nameInRussianLangField);
		nameInRussianLangField.sendKeys(nameInRussianLang);
	}

	public void nameInEnglishField(String nameInEnglish) {
		nameInEnglishField.sendKeys(nameInEnglish);
	}

	public void tinNumberField(String tinNumber) {
		tinNumberField.sendKeys(tinNumber);
	}

	public void genderDropdownField() {
		safeClick(genderDropdownField);
	}

	public void genderDropdownOptions(String gender) {
		safeClick(genderDropdownField);

		for (WebElement option : genderDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(gender)) {
				safeClick(option);
				return;
			}
		}
	}

	public void workOrderNumberField(String workOrderNumber) {
		safeClick(workOrderNumberField);
		workOrderNumberField.sendKeys(workOrderNumber);
	}

	public void dobFieldPersonalInfo(String dob) {
		enterDate(dobFieldPersonalInfo, dob);
	}

	public void maritalStatusDropdownField() {
		safeClick(maritalStatusDropdownField);
	}

	public void maritalStatusDropdownOptions(String maritalStatus) {

		// Open dropdown
		safeClick(maritalStatusDropdownField);

		boolean found = false;

		// Try selecting existing value
		for (WebElement option : maritalStatusDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(maritalStatus)) {
				safeClick(option);
				found = true;
				break;
			}
		}

		// If not found → add new marital status
		if (!found) {

			// Click +
			safeClick(addMaritalStatusPlusButton);

			// Enter marital status
			safeClick(maritalStatusInputField);
			maritalStatusInputField.sendKeys(maritalStatus);

			// Save
			safeClick(saveMaritalStatusButton);

			// OK
			safeClick(saveOkButtonPersonalInfo);

			// Reopen dropdown
			safeClick(maritalStatusDropdownField);

			// Select newly added value
			for (WebElement option : maritalStatusDropdownOptions) {
				if (option.getText().trim().equalsIgnoreCase(maritalStatus)) {
					safeClick(option);
					return;
				}
			}

			throw new RuntimeException("Marital Status value not found even after adding: " + maritalStatus);
		}
	}

	public void addMaritalStatusPlusButton() {
		blinkElement(addMaritalStatusPlusButton);
		safeClick(addMaritalStatusPlusButton);
	}

	public void maritalStatusInputField(String maritalStatus) {
		safeClick(maritalStatusInputField);
		maritalStatusInputField.sendKeys(maritalStatus);
	}

	public void saveMaritalStatusButton() {
		blinkElement(saveMaritalStatusButton);
		safeClick(saveMaritalStatusButton);
	}

	public void dateOfJoiningField(String dateOfJoining) {
		enterDate(dateOfJoiningField, dateOfJoining);
	}

	public void emailFieldPersonalInfo(String email) {
		emailFieldPersonalInfo.sendKeys(email);
	}

	public void selectCountryCodeField() {
		safeClick(selectCountryCodeField);
	}

	public void selectCountryCodeOptions(String countryCode) {
		safeClick(selectCountryCodeField);

		for (WebElement option : selectCountryCodeOptions) {
			if (option.getText().trim().equalsIgnoreCase(countryCode)) {
				safeClick(option);
				return;
			}
		}
	}

	public void mobileNumberField(String mobileNumber) {
		safeClick(mobileNumberField);
		mobileNumberField.sendKeys(mobileNumber);
	}

	public void addressField(String address) {
		addressField.clear();
		addressField.sendKeys(address);
	}

	public void addressLine2Field(String addressLine2) {
		addressLine2Field.clear();
		addressLine2Field.sendKeys(addressLine2);
	}

	public void saveButtonPersonalInfo() {
		blinkElement(saveButtonPersonalInfo);
		try {
			captureScreenshot("Personal InfoFormation Filled");
		} catch (Exception e) {
			e.printStackTrace();
		}
		safeClick(saveButtonPersonalInfo);
		handleAlertIfPresent();
	}

	public void saveOkButtonPersonalInfo() {
		blinkElement(saveOkButtonPersonalInfo);
		handleModalOk(saveOkButtonPersonalInfo);
	}

	// fill the personal information form
	public void fillPersonalInformationForm(String russianName, String englishName, String tinNumber,
			String workOrderNumber, String dob, String gender, String maritalStatus, String dateOfJoining, String email,
			String countryCode, String mobileNumber, String address, String addressLine2) {
		addPersonalInfoBtn();
		nameInRussianLangField(russianName);
		nameInEnglishField(englishName);
		tinNumberField(tinNumber);
		workOrderNumberField(workOrderNumber);
		dobFieldPersonalInfo(dob);
		genderDropdownField();
		genderDropdownOptions(gender);
		maritalStatusDropdownOptions(maritalStatus);
		dateOfJoiningField(dateOfJoining);
		emailFieldPersonalInfo(email);
		selectCountryCodeOptions(countryCode);
		mobileNumberField(mobileNumber);
		addressField(address);
		addressLine2Field(addressLine2);
		saveButtonPersonalInfo();
		saveOkButtonPersonalInfo();
	}

}
