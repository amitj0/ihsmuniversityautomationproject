package com.ihsm.university.testcases.flows.classschedule;

import org.openqa.selenium.WebDriver;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.pageobjects.classchedule.IHSM_ClassSchedule;

public class IHSM_ClassScheduleInfo extends BaseClass {

	private WebDriver driver;

	public IHSM_ClassScheduleInfo(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyClassScheduleInfo() throws InterruptedException {

		IHSM_ClassSchedule classInfo = new IHSM_ClassSchedule(driver);
		classInfo.fillClassSchedulingInformation("2026 -2027", "1", 2,
				1, 0);
		
	}

}
