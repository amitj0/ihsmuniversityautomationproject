package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.base.FlowStep;
import com.ihsm.university.testcases.flows.student.*;
import com.ihsm.university.utilities.FlowStateUtils;

public class IHSM_StudentRegistrationFullWorkFlowTest extends BaseClass {

    @Test(groups = "regression", description = "Verify Full Student Registration Flow with FlowStep support", 
          dataProvider = "studentData", dataProviderClass = StudentFullRegistrationDataProvider.class)
    public void verifyStudentRegistrationFullFlow(StudentFullRegistrationDataVariables student) throws Exception {

        logger.info("==== STARTING FULL STUDENT REGISTRATION FLOW ======");

        FlowStep lastStep = FlowStateUtils.getLastCompletedStep();

        if (shouldRun(lastStep, FlowStep.BASIC_INFORMATION)) {
            new IHSM_FullBasicInformationFlow(getDriver()).execute(student);
            FlowStateUtils.saveStep(FlowStep.BASIC_INFORMATION);
            logger.info("Basic Information filled successfully");
        }

        if (shouldRun(lastStep, FlowStep.DOCUMENTS)) {
            new IHSM_FullDocumentsFlow(getDriver()).execute(student);
            FlowStateUtils.saveStep(FlowStep.DOCUMENTS);
            logger.info("Documents Information filled successfully");
        }

        if (shouldRun(lastStep, FlowStep.ACADEMICS)) {
            new IHSM_FullAcademicsFlow(getDriver()).execute(student);
            FlowStateUtils.saveStep(FlowStep.ACADEMICS);
            logger.info("Academics Information filled successfully");
        }

        if (shouldRun(lastStep, FlowStep.STATUS)) {
            new IHSM_FullStatusFlow(getDriver()).execute(student);
            FlowStateUtils.saveStep(FlowStep.STATUS);
            logger.info("Status Information filled successfully");
        }

        logger.info("===== FULL STUDENT REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");
    }

    /* ================= FLOW CONTROL ================= */
    private boolean shouldRun(FlowStep last, FlowStep current) {
        return last == null || last.ordinal() < current.ordinal();
    }
}
