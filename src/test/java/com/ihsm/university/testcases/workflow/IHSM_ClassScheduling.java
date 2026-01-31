package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.testcases.flows.classschedule.IHSM_ClassScheduleInfo;

public class IHSM_ClassScheduling extends BaseClass {
	
	
	@Test(description = "Filling the class scheduling Information")
	public void fillClassInformation() throws InterruptedException {
		
		IHSM_ClassScheduleInfo info = new IHSM_ClassScheduleInfo(getDriver());
		info.verifyClassScheduleInfo();
		logger.info("Class Scheduled Successfully");
		
	}
	
	
	

}
