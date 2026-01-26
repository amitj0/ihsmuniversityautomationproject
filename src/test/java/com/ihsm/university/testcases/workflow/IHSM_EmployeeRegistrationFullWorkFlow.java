package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.testcases.flows.employee.IHSM_FullBasicInformationFlow;

public class IHSM_EmployeeRegistrationFullWorkFlow extends BaseClass {

	@Test(description = "Verify Full Employee Registration Flow in IHSM University Application")
	public void verifyEmployeeRegistrationFullFlow() throws Exception {
		System.out.println("==== STARTING FULL EMPLOYEE REGISTRATION FLOW ====== ");

		IHSM_FullBasicInformationFlow basicInformationFlow = new IHSM_FullBasicInformationFlow();
		basicInformationFlow.execute();
		logger.info("Basic Information filled successfully..........");

		System.out.println("===== FULL EMPLOYEE REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");

	}

}
