package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.testcases.flows.student.IHSM_FullAcademicsFlow;
import com.ihsm.university.testcases.flows.student.IHSM_FullBasicInformationFlow;
import com.ihsm.university.testcases.flows.student.IHSM_FullDocumentsFlow;
import com.ihsm.university.testcases.flows.student.IHSM_FullStatusFlow;

public class IHSM_StudentRegistrationFullWorkFlow extends BaseClass {

	@Test(description = "Verify Full Student Registration Flow in IHSM University Application")
	public void verifyStudentRegistrationFullFlow() throws Exception {
		System.out.println("==== STARTING FULL STUDENT REGISTRATION FLOW ====== ");

		new IHSM_FullBasicInformationFlow().execute();
		logger.info("Basic Information filled successfully..........");

		new IHSM_FullDocumentsFlow().execute();
		logger.info("Documents Information filled successfully..........");

		new IHSM_FullAcademicsFlow().execute();
		logger.info("Academics Information filled successfully..........");

		new IHSM_FullStatusFlow().execute();
		logger.info("Status Information filled successfully..........");

		System.out.println("===== FULL STUDENT REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");

	}

}
