package com.ihsm.university.pageobjects.employee.basicinformation;

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

	@FindBy(xpath = "//div[@id='VaccinationModelID']//label[contains(normalize-space(),'Type')]/following::span[contains(@class,'addvalue')][1]")
	private WebElement addVaccinationTypePlusButton;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//input[@name='strMasterName']")
	private WebElement vaccinationTypeInputField;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveVaccinationTypeButton;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//ng-select[@name='intdose']")
	private WebElement vaccinationPhaseDropdownField;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//label[contains(normalize-space(),'Phase')]/following::span[contains(@class,'addvalue')][1]")
	private WebElement addVaccinationPhasePlusButton;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//input[@name='strMasterName']")
	private WebElement addVaccFieldInput;

	@FindBy(xpath = "//div[@id='AddMasterDataModal']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement addSaveBtn;

	@FindBy(xpath = "//div[@id='VaccinationModelID']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveButton;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement okButton;

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
		selectNgDropdownValue(vaccinationTypeDropdownField, vaccinationType, addVaccinationTypePlusButton,
				vaccinationTypeInputField, saveVaccinationTypeButton, okButtonSuccessPopup);
	}

	public void vaccinationPhaseDropdownField() {
		safeClick(vaccinationPhaseDropdownField);
	}

	public void selectVaccinationPhaseOption(String vaccinationPhase) {
		selectNgDropdownValue(vaccinationPhaseDropdownField, // dropdown
				vaccinationPhase, // value
				addVaccinationPhasePlusButton, // "+" button for phase
				addVaccFieldInput, // input field for new phase
				addSaveBtn, // save button in modal
				okButton); // OK modal button
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
		handleSubmissionConfirmation();
	}

	public void okButtonSuccessPopup() {
		blinkElement(okButtonSuccessPopup);
		handleModalOk(okButtonSuccessPopup);
	}

	public boolean isVaccInfoSavedSuccessfully() {
		return okButtonSuccessPopup.isDisplayed();
	}

	// fill the vaccination form
	public BasicInfo_BiometricsInformation fillVaccinationForm(String vaccinationType, String vaccinationPhase,
			String certificateNumber, String vaccinationDate, String remarks) {
		addVaccinationInfoBtn();
		vaccinationTypeDropdownField();
		selectVaccinationTypeOption(vaccinationType);
		vaccinationPhaseDropdownField();
		selectVaccinationPhaseOption(vaccinationPhase);
		fillVaccinationCertificateNumber(certificateNumber);
		fillVaccinationDate(vaccinationDate);
		fillRemarks(remarks);
		saveVaccinationInfoBtn();
		return new BasicInfo_BiometricsInformation(driver);
	}

}
