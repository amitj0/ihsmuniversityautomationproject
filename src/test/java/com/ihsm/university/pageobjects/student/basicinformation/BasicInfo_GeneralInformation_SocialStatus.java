package com.ihsm.university.pageobjects.student.basicinformation;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class BasicInfo_GeneralInformation_SocialStatus extends BasePage {

	public BasicInfo_GeneralInformation_SocialStatus(WebDriver driver) {
		super(driver);
	}

	// locate the web element here
	@FindBy(xpath = "//span[@data-bs-target='#GeneralInfoId']")
	private WebElement generalInfoTab;

	@FindBy(xpath = "//a[@href='#tab22' and normalize-space(text())='Social Status']")
	private WebElement socialStatusTab;

	@FindBy(xpath = "//div[@id='tab22']//ng-select[@name='TYPE']")
	private WebElement socialStatusField;

	@FindBy(xpath = "//div[@role='listbox' and contains(@class, 'ng-dropdown-panel-items')]/div/div")
	private List<WebElement> socialStatusFieldList;

	@FindBy(xpath = "(//div[@id='GeneralInfoId']//label[contains(normalize-space(),'Type')]/following::span[contains(@class,'addvalue')])[2]")
	private WebElement addSocialStatusPlusBtn;

	@FindBy(xpath = "//div[@id='AddMasterDataModal1ID']//input[@name='ENTER_VALUE']")
	private WebElement socialStatusInputField;

	@FindBy(xpath = "//div[@id='AddMasterDataModal1ID']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveSocialStatusBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement socialStatusSaveOkBtn;

	@FindBy(xpath = "//div[@id='tab22']//input[@type='file']")
	private WebElement dragdropFileFieldSocial;

	@FindBy(xpath = "//div[@id='tab22']//button[contains(@class, 'btnprimary') and text()='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//div[@id='AlertSuccesModal' and contains(@class,'show')]//button[normalize-space()='Ok']")
	private WebElement saveOkBtn;

	// methods to perform the action

	public void generalInfoTab() {
		blinkElement(generalInfoTab);
		safeClick(generalInfoTab);
	}

	public void socialStatusTab() {
		safeClick(socialStatusTab);
	}

	public void socialStatusField() {
		safeClick(socialStatusField);
	}

	public void socialStatusFieldList(String socialStatus) {
		safeClick(socialStatusField);
		boolean found = false;
		for (WebElement option : socialStatusFieldList) {
			if (option.getText().trim().equalsIgnoreCase(socialStatus)) {
				safeClick(option);
				found = true;
				break;
			}
		}

		if (!found) {
			safeClick(addSocialStatusPlusBtn);
			safeClick(socialStatusInputField);
			socialStatusInputField.sendKeys(socialStatus);
			safeClick(saveSocialStatusBtn);
			safeClick(socialStatusSaveOkBtn);
			// select the newly added option
			safeClick(socialStatusField);
			for (WebElement option : socialStatusFieldList) {
				if (option.getText().trim().equalsIgnoreCase(socialStatus)) {
					safeClick(option);
					break;
				}
			}
		}
	}

	public void dragdropFileFieldSocial(String filePath) {
		dragdropFileFieldSocial.sendKeys(filePath);
	}

	public void saveBtn() {
		blinkElement(saveBtn);
		try {
			captureScreenshot("General Social Information Saved");
		} catch (IOException e) {
			e.printStackTrace();
		}
		safeClick(saveBtn);
		handleAlertIfPresent();
	}

	public void saveOkBtn() {
		blinkElement(saveOkBtn);
		handleModalOk(saveOkBtn);
	}
	
	public boolean isSocialStatusSavedSuccessfully() {
		return saveOkBtn.isDisplayed();
	}

	// fill the social status form
	public BasicInfo_GeneralInformation_SocialWorkLocation fillSocialStatusForm(String socialStatus, String filePath) {
		generalInfoTab();
		socialStatusTab();
		socialStatusField();
		socialStatusFieldList(socialStatus);
		dragdropFileFieldSocial(filePath);
		saveBtn();
		saveOkBtn();
		return new BasicInfo_GeneralInformation_SocialWorkLocation(driver);
	}

}
