package com.ihsm.university.pageobjects.employee.basicinformation;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_VaccinationInformation extends BasePage {

	public BasicInfo_VaccinationInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element here
	@FindBy(xpath = "//div[@id='divbtnVaccination']//span")
	private WebElement addVaccinationInfoBtn;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//ng-select[@name='strVaccinationType']")
	private WebElement vaccinationTypeDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> vaccinationTypeDropdownOptions;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//label[contains(normalize-space(),'Type')]/following::span[contains(@class,'addvalue')][1]")
	private WebElement addVaccinationTypePlusButton;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//input[@name='strMasterName']")
	private WebElement vaccinationTypeInputField;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveVaccinationTypeButton;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//ng-select[@name='intdose']")
	private WebElement vaccinationPhaseDropdownField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> vaccinationPhaseDropdownOptions;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//label[contains(normalize-space(),'Phase')]/following::span[contains(@class,'addvalue')][1]")
	private WebElement addVaccinationPhasePlusButton;

	@FindBy(name = "strVaccinationCertificateNumber")
	private WebElement vaccinationCertificateNumberField;

	@FindBy(name = "dttVaccinationDate")
	private WebElement vaccinationDateField;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//textarea[@id='exampleFormControlTextarea1']")
	private WebElement remarksField;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveVaccinationInfoBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement okButtonSuccessPopup;

	// methods to perform the action
	public void addVaccinationInfoBtn() {
		blinkElement(addVaccinationInfoBtn);
		safeClick(addVaccinationInfoBtn);
	}

	public void vaccinationTypeDropdownField() {
		safeClick(vaccinationTypeDropdownField);
	}

	public void selectVaccinationTypeOption(String vaccinationType) {

		// Open dropdown
		safeClick(vaccinationTypeDropdownField);

		boolean found = false;

		// Try selecting existing value
		for (WebElement option : vaccinationTypeDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(vaccinationType)) {
				safeClick(option);
				found = true;
				break;
			}
		}

		// If not found → add new Vaccination Type
		if (!found) {

			// Click +
			safeClick(addVaccinationTypePlusButton);

			// Enter vaccination type
			safeClick(vaccinationTypeInputField);
			vaccinationTypeInputField.sendKeys(vaccinationType);

			// Save
			safeClick(saveVaccinationTypeButton);

			// Ok
			safeClick(okButtonSuccessPopup);

			// Reopen dropdown
			safeClick(vaccinationTypeDropdownField);

			// Select newly added value
			for (WebElement option : vaccinationTypeDropdownOptions) {
				if (option.getText().trim().equalsIgnoreCase(vaccinationType)) {
					safeClick(option);
					return;
				}
			}

			throw new RuntimeException("Vaccination Type value not found even after adding: " + vaccinationType);
		}
	}

	public void vaccinationPhaseDropdownField() {
		safeClick(vaccinationPhaseDropdownField);
	}

	public void selectVaccinationPhaseOption(String vaccinationPhase) {

		// Open dropdown
		safeClick(vaccinationPhaseDropdownField);

		boolean found = false;

		// Try selecting existing value
		for (WebElement option : vaccinationPhaseDropdownOptions) {
			if (option.getText().trim().equalsIgnoreCase(vaccinationPhase)) {
				safeClick(option);
				found = true;
				break;
			}
		}

		// If not found → add new Vaccination Phase
		if (!found) {

			// Click +
			safeClick(addVaccinationPhasePlusButton);

			// Enter vaccination phase
			safeClick(vaccinationTypeInputField);
			vaccinationTypeInputField.sendKeys(vaccinationPhase);

			// Save
			safeClick(saveVaccinationTypeButton);

			// Ok
			safeClick(okButtonSuccessPopup);

			// Reopen dropdown
			safeClick(vaccinationPhaseDropdownField);

			// Select newly added value
			for (WebElement option : vaccinationPhaseDropdownOptions) {
				if (option.getText().trim().equalsIgnoreCase(vaccinationPhase)) {
					safeClick(option);
					return;
				}
			}

			throw new RuntimeException("Vaccination Phase value not found even after adding: " + vaccinationPhase);
		}
	}

	public void fillVaccinationCertificateNumber(String certificateNumber) {
		vaccinationCertificateNumberField.sendKeys(certificateNumber);
	}

	public void fillVaccinationDate(String vaccinationDate) {
		enterDate(vaccinationDateField, vaccinationDate);
	}

	public void fillRemarks(String remarks) {
		remarksField.sendKeys(remarks);
	}

	public void saveVaccinationInfoBtn() {
		blinkElement(saveVaccinationInfoBtn);
		try {
			captureScreenshot("Vaccination InfoFormation Filled");
		} catch (Exception e) {
			e.printStackTrace();
		}

		safeClick(saveVaccinationInfoBtn);
		handleAlertIfPresent();
	}

	public void okButtonSuccessPopup() {
		blinkElement(okButtonSuccessPopup);
		handleModalOk(okButtonSuccessPopup);
	}

	// fill the vaccination form
	public void fillVaccinationForm(String vaccinationType, String vaccinationPhase, String certificateNumber,
			String vaccinationDate, String remarks) {
		addVaccinationInfoBtn();
		vaccinationTypeDropdownField();
		selectVaccinationTypeOption(vaccinationType);
		vaccinationPhaseDropdownField();
		selectVaccinationPhaseOption(vaccinationPhase);
		fillVaccinationCertificateNumber(certificateNumber);
		fillVaccinationDate(vaccinationDate);
		fillRemarks(remarks);
		saveVaccinationInfoBtn();
		okButtonSuccessPopup();
	}

}
