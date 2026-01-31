package com.ihsm.university.pageobjects.classchedule;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ihsm.university.base.BasePage;

public class IHSM_ClassSchedule extends BasePage {

	public IHSM_ClassSchedule(WebDriver driver) {
		super(driver);
	}

	// locate the web element here

	@FindBy(xpath = "//a[@id='a5']//span[normalize-space()='Course Planner']")
	private WebElement coursePlannerTab;

	@FindBy(xpath = "//a[@href='#/ClassSchedule']")
	private WebElement classSchedule;

	@FindBy(name = "intSessionId")
	private WebElement sessionField;

	@FindBy(xpath = "//ng-select[@name='intSessionId']//div[@role='option']")
	private List<WebElement> sessionFieldList;

	@FindBy(xpath = "//div[@id='Tab1']//ng-select[@name='strBatchId']")
	private WebElement batchField;

	@FindBy(xpath = "//ng-select[@name='strBatchId']//div[@role='option']")
	private List<WebElement> batchFieldList;

	@FindBy(xpath = "//div[@id='Tab1']//ng-select[@name='strAcademicPlanId']")
	private WebElement academicPlanField;

	

	@FindBy(name = "intSemesterId")
	private WebElement semField;

	@FindBy(xpath = "(//ng-select[@name='strAcademicPlanId']//div[@role='option'])[3]")
	private List<WebElement> semFieldList;
	  





	@FindBy(xpath = "//div[@id='Tab1']//ng-select[@name='SUBJECT_TYPE']")
	private WebElement subjectTypeField;

	@FindBy(xpath = "//div[@id='Tab1']//button[contains(@class, 'btnprimary') and text()='Search']")
	private WebElement searchButton;

	// method to perform the action on these elements
	// -------------------- Navigation --------------------

	public void openCoursePlannerTab() {
		safeClick(coursePlannerTab);
	}

	public void openClassSchedule() {
		safeClick(classSchedule);
	}

	// -------------------- Session --------------------

	public void selectSession(String sessionName) {
		safeClick(sessionField);

		for (WebElement option : sessionFieldList) {
			if (option.getText().trim().equalsIgnoreCase(sessionName)) {
				safeClick(option);
				return;
			}
		}
	}

	// -------------------- Batch --------------------

	public void selectBatch(String batchName) {
		safeClick(batchField);

		for (WebElement option : batchFieldList) {
			if (option.getText().trim().equalsIgnoreCase(batchName)) {
				safeClick(option);
				return;
			}
		}
	}

	// -------------------- Academic Plan --------------------

	public void selectAcademicPlanByIndex(int index) {

	    // Open dropdown
	    safeClick(academicPlanField);

	    // Build xpath using index
	    WebElement option = academicPlanField.findElement(
	        By.xpath(".//div[@role='option' and contains(@id,'-" + index + "')]")
	    );

	    safeClick(option);
	}


	// -------------------- Semester --------------------

	public void selectSemester(int semesterIndex) {
		safeClick(semField);

	    // Build xpath using index
	    WebElement option = semField.findElement(
	        By.xpath(".//div[@role='option' and contains(@id,'-" + semesterIndex + "')]")
	    );
	    safeClick(option);
	}

	// -------------------- Subject Type --------------------

	public void selectSubjectType(int subjectTypeIndex) {
		safeClick(subjectTypeField);

		  // Build xpath using index
	    WebElement option = subjectTypeField.findElement(
	        By.xpath(".//div[@role='option' and contains(@id,'-" + subjectTypeIndex + "')]")
	    );
	    safeClick(option);
	}

	// -------------------- Search --------------------

	public void clickSearch() {
		safeClick(searchButton);
	}

	// fill the class scheduling information

	public void fillClassSchedulingInformation(String session, String batch, int optionIndex, int sem,
			int subject) throws InterruptedException {
		openCoursePlannerTab();
		openClassSchedule();
		selectSession(session);
		selectBatch(batch);
		selectAcademicPlanByIndex(optionIndex);
		selectSemester(sem);
		Thread.sleep(5000);
		selectSubjectType(subject);
		clickSearch();

	}

}
