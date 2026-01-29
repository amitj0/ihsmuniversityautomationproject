package com.ihsm.university.navigation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class Student_Search extends BasePage {

	public Student_Search(WebDriver driver) {
		super(driver);
	}

	// locate the web elements here
	@FindBy(xpath = "//a[@id='a3']")
	private WebElement studentSearchMenu;

	@FindBy(xpath = "//a[@href='#/Student/SearchStudents']")
	private WebElement searchStudentsSubMenu;

	@FindBy(xpath = "(//div[@id='SearchStudent']//input)[1]")
	private WebElement studentSearchField;

	@FindBy(xpath = "//div[@class='card-footer']//button[normalize-space()='Search']")
	private WebElement searchButton;

	@FindBy(xpath = "(//table[@id='tblStudentInfo']//tbody//td)[7]")
	private WebElement studentEnrollnmentNumber;

	// methods to perform the action
	public void studentSearchMenu() {
		blinkElement(studentSearchMenu);
		safeClick(studentSearchMenu);
	}

	public void searchStudentsSubMenu() {
		blinkElement(searchStudentsSubMenu);
		safeClick(searchStudentsSubMenu);
	}

	public void enterStudentDetails(String details) {
		studentSearchField.sendKeys(details);
	}

	public void clickSearchButton() {
		blinkElement(searchButton);
		safeClick(searchButton);
	}

	public String getStudentEnrollmentNumber() {
		blinkElementToVerify(studentEnrollnmentNumber);
		String enroll = studentEnrollnmentNumber.getText();
		return enroll;
	}

	// fill student search info here
	public void fillStudentSearchInfo(String details) {
		studentSearchMenu();
		searchStudentsSubMenu();
		enterStudentDetails(details);
		clickSearchButton();
	}

}
