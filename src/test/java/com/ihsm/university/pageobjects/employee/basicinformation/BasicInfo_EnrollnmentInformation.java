package com.ihsm.university.pageobjects.employee.basicinformation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_EnrollnmentInformation extends BasePage {

	public BasicInfo_EnrollnmentInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element
	@FindBy(id = "a4")
	private WebElement employmentDetailsTab;

	@FindBy(xpath = "//a[@href='#/Employee/NewEnrollEmployee']")
	private WebElement enrollnmentInformationLink;

	@FindBy(xpath = "(//span[@class='adddetailbtn'])[1]")
	private WebElement addEnrollnmentInfoBtn;

	@FindBy(name = "FIRSTNAME")
	private WebElement firstNameField;

	@FindBy(name = "SURNAME")
	private WebElement nameInEnglishField;

	@FindBy(xpath = "//div[@id='divEmployeeRegistration']//ng-select[@name='GENDER']")
	private WebElement genderDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> genderDropdownOptions;

	@FindBy(xpath = "//div[@id='divEmployeeRegistration']//input[@name='IIN']")
	private WebElement iinNumberField;

	@FindBy(xpath = "//div[@id='divEmployeeRegistration']//input[@name='EMAIL']")
	private WebElement emailField;

	@FindBy(xpath = "//div[@id='divEmployeeRegistration']//ng-select[@name='strCitizenShip']")
	private WebElement citizenshipDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> citizenshipDropdownOptions;

	@FindBy(xpath = "(//div[@id='divPersonalInfoMaodel']//span[@data-bs-target='#addvalue'])[2]")
	private WebElement addCitizenshipPlusButton;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//input[@name='strMasterName']")
	private WebElement citizenshipInputField;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveCitizenshipButton;

	@FindBy(xpath = "//div[contains(@class,'modal-content')]//button[normalize-space()='Save']")
	private WebElement saveButtonEnrollnmentInfo;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkButtonEnrollnmentInfo;

	// methods to access the web elements
	public void clickEmploymentDetailsTab() {
		blinkElement(employmentDetailsTab);
		safeClick(employmentDetailsTab);
	}

	public void clickEnrollnmentInformationLink() {
		blinkElement(enrollnmentInformationLink);
		safeClick(enrollnmentInformationLink);
	}

	public void clickAddEnrollnmentInfoBtn() {
		blinkElement(addEnrollnmentInfoBtn);
		safeClick(addEnrollnmentInfoBtn);
	}

	public void enterFirstName(String firstName) {
		safeClick(firstNameField);
		firstNameField.sendKeys(firstName);
	}

	public void enterNameInEnglish(String nameInEnglish) {
		nameInEnglishField.sendKeys(nameInEnglish);
	}

	public void genderDropdownField() {
		safeClick(genderDropdownField);
	}

	public void selectGenderOption(String gender) {
		safeClick(genderDropdownField);
		for (WebElement option : genderDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(gender)) {
				safeClick(option);
				return;
			}
		}

	}

	public void enterIINNumber(String iinNumber) {
		iinNumberField.sendKeys(iinNumber);
	}

	public void enterEmail(String email) {
		emailField.sendKeys(email);
	}

	public void citizenshipDropdownField() {
		safeClick(citizenshipDropdownField);
	}

	public void selectCitizenshipOption(String citizenship) {

		safeClick(citizenshipDropdownField);

		boolean found = false;

		for (WebElement option : citizenshipDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(citizenship)) {
				safeClick(option);
				found = true;
				break;
			}
		}

		// If not found → add new citizenship
		if (!found) {

			// Click +
			safeClick(addCitizenshipPlusButton);

			// Enter citizenship
			safeClick(citizenshipInputField);
			citizenshipInputField.sendKeys(citizenship);

			// Save
			safeClick(saveCitizenshipButton);

			// Ok
			safeClick(saveOkButtonEnrollnmentInfo);

			// Reopen dropdown
			safeClick(citizenshipDropdownField);

			// Select newly added value
			for (WebElement option : citizenshipDropdownOptions) {
				if (option.getText().trim().equalsIgnoreCase(citizenship)) {
					safeClick(option);
					return;
				}
			}

			throw new RuntimeException("Citizenship value not found even after adding: " + citizenship);
		}
	}

	public void clickSaveButtonEnrollnmentInfo() {
		blinkElement(saveButtonEnrollnmentInfo);
		try {
			captureScreenshot("Enrollnment InfoFormation Filled");
		} catch (Exception e) {
			e.printStackTrace();
		}
		safeClick(saveButtonEnrollnmentInfo);
		handleAlertIfPresent();
	}

	public void clickSaveOkButtonEnrollnmentInfo() {
		blinkElement(saveOkButtonEnrollnmentInfo);
		handleModalOk(saveOkButtonEnrollnmentInfo);
	}

	// fill the enrollnment information form
	public BasicInfo_EnrollnmentInformation fillEnrollnmentInformationForm(String firstName, String nameInEnglish, String gender, String iinNumber,
			String email, String citizenship) {
		clickEmploymentDetailsTab();
		clickEnrollnmentInformationLink();
		clickAddEnrollnmentInfoBtn();
		enterFirstName(firstName);
		enterNameInEnglish(nameInEnglish);
		genderDropdownField();
		selectGenderOption(gender);
		enterIINNumber(iinNumber);
		enterEmail(email);
		citizenshipDropdownField();
		selectCitizenshipOption(citizenship);
		return new BasicInfo_EnrollnmentInformation(driver);

	}

}
