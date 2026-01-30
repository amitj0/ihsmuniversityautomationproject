package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.testcases.flows.employee.IHSM_FullBasicInformationFlow;
import com.ihsm.university.testcases.flows.employee.IHSM_FullDesignationFlow;
import com.ihsm.university.testcases.flows.employee.IHSM_FullDocumentsFlow;
import com.ihsm.university.testcases.flows.employee.IHSM_FullProfessionalInformation;

public class IHSM_EmployeeRegistrationFullWorkFlow extends BaseClass {

	@Test(groups = "regression", description = "Verify Full Employee Registration Flow in IHSM University Application")
	public void verifyEmployeeRegistrationFullFlow() throws Exception {
		System.out.println("==== STARTING FULL EMPLOYEE REGISTRATION FLOW ====== ");

		IHSM_FullBasicInformationFlow basicInformationFlow = new IHSM_FullBasicInformationFlow();
		basicInformationFlow.execute();
		logger.info("Basic Information filled successfully..........");

		IHSM_FullDesignationFlow designInformationFlow = new IHSM_FullDesignationFlow();
		designInformationFlow.execute();
		logger.info("Designation Information filled successfully.......");

		IHSM_FullProfessionalInformation profInfoFlow = new IHSM_FullProfessionalInformation();
		profInfoFlow.execute();
		logger.info("Professional Information filled successfully.......");

		IHSM_FullDocumentsFlow docInfoFlow = new IHSM_FullDocumentsFlow();
		docInfoFlow.execute();
		logger.info("Documents Information filled successfully........");
		
		

		System.out.println("===== FULL EMPLOYEE REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");

	}

}
