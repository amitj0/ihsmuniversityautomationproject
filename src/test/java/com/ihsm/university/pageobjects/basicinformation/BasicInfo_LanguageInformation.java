package com.ihsm.university.pageobjects.basicinformation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_LanguageInformation extends BasePage {

	public BasicInfo_LanguageInformation(WebDriver driver) {
		super(driver);
	}

	// locate the web element here
	@FindBy(xpath = "//span[@data-bs-target='#languageid']")
	private WebElement addLangBtn;

	@FindBy(xpath = "(//div[@id='languageid']//ng-select[@name='VACCINATIONTYPE']//div[@class='ng-input'])[1]")
	private WebElement langField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> langFieldList;

	@FindBy(xpath = "//label[text()=' LEVEL']/following-sibling::div//div[contains(@class,'ng-select-container')]")
	private WebElement langLvlField;

	@FindBy(xpath = "//div[contains(@class,'ng-dropdown-panel')]//div[@role='option']")
	private List<WebElement> langLvlFieldList;

	@FindBy(xpath = "//h5[normalize-space()='Language']/ancestor::div[@class='modal-content']//button[normalize-space()='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkBtn;

	// methods to perform the action
	public void addLangBtn() {
		blinkElement(addLangBtn);
		safeClick(addLangBtn);
	}

	public void langField() {
		safeClick(langField);
	}

	public void langFieldList(String language) {
		for (WebElement option : langFieldList) {
			if (option.getText().trim().equalsIgnoreCase(language)) {
				safeClick(option);
				break;
			}
		}
	}

	public void langLvlField() {
		safeClick(langLvlField);
	}

	public void langLvlFieldList(String level) {
		for (WebElement option : langLvlFieldList) {
			if (option.getText().trim().equalsIgnoreCase(level)) {
				safeClick(option);
				break;
			}
		}
	}

	public void saveBtn() {
		blinkElement(saveBtn);
		try {
			captureScreenshot("Language Information Saved");
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

	// fill the language information form
	public BasicInfo_GeneralInformation_Prerights fillLanguageInformationForm(String language, String level) {
		addLangBtn();
		langField();
		langFieldList(language);
		langLvlField();
		langLvlFieldList(level);
		saveBtn();
		saveOkBtn();
		
		return new BasicInfo_GeneralInformation_Prerights(driver);
	}

}
