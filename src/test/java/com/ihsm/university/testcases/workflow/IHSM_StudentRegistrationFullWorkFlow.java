package com.ihsm.university.testcases.workflow;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ihsm.university.base.BaseClass;
import com.ihsm.university.navigation.Student_Search;
import com.ihsm.university.testcases.flows.student.*;

public class IHSM_StudentRegistrationFullWorkFlow extends BaseClass {

	@Test(groups = "regression", description = "Verify Full Student Registration Flow in IHSM University Application", dataProvider = "studentData", dataProviderClass = StudentFullRegistrationDataProvider.class)
	public void verifyStudentRegistrationFullFlow(StudentFullRegistrationDataVariables student) throws Exception {

		logger.info("==== STARTING FULL STUDENT REGISTRATION FLOW ======");

		// ---------------- Basic Information ----------------
		new IHSM_FullBasicInformationFlow(getDriver()).execute(student);
		logger.info("Basic Information filled successfully");

		// ---------------- Documents ----------------
		new IHSM_FullDocumentsFlow(getDriver()).execute(student);
		logger.info("Documents Information filled successfully");

		// ---------------- Academics ----------------
		new IHSM_FullAcademicsFlow(getDriver()).execute(student);
		logger.info("Academics Information filled successfully");

		// ---------------- Status ----------------
		new IHSM_FullStatusFlow(getDriver()).execute(student);
		logger.info("Status Information filled successfully");

		logger.info("===== FULL STUDENT REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");
	}
}
