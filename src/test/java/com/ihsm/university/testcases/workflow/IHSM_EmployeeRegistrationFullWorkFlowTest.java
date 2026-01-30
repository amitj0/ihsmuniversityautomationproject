package com.ihsm.university.testcases.workflow;

import org.testng.annotations.Test;

import com.ihsm.university.base.BaseClass;
import com.ihsm.university.base.FlowStep;
import com.ihsm.university.testcases.flows.employee.IHSM_FullBasicInformationFlowTest;
import com.ihsm.university.testcases.flows.employee.IHSM_FullDesignationFlowTest;
import com.ihsm.university.testcases.flows.employee.IHSM_FullProfessionalInformationFlowTest;
import com.ihsm.university.testcases.flows.employee.IHSM_FullDocumentsFlowTest;
import com.ihsm.university.utilities.FlowStateUtils;

public class IHSM_EmployeeRegistrationFullWorkFlowTest extends BaseClass {

    @Test(groups = "regression",
          description = "Verify Full Employee Registration Flow in IHSM University Application")
    public void verifyEmployeeRegistrationFullFlow() throws Exception {

        logger.info("===== STARTING FULL EMPLOYEE REGISTRATION FLOW =====");

        FlowStep lastStep = FlowStateUtils.getLastCompletedStep();

        if (shouldRun(lastStep, FlowStep.BASIC_INFO_FLOW)) {
            new IHSM_FullBasicInformationFlowTest().verifyFullBasicInformation();
            FlowStateUtils.saveStep(FlowStep.BASIC_INFO_FLOW);
        }

        if (shouldRun(lastStep, FlowStep.DESIGNATION_FLOW)) {
            new IHSM_FullDesignationFlowTest().verifyFullDesignationInformation();
            FlowStateUtils.saveStep(FlowStep.DESIGNATION_FLOW);
        }

        if (shouldRun(lastStep, FlowStep.PROFESSIONAL_FLOW)) {
            new IHSM_FullProfessionalInformationFlowTest().verifyFullProfessionalInformation();
            FlowStateUtils.saveStep(FlowStep.PROFESSIONAL_FLOW);
        }

        if (shouldRun(lastStep, FlowStep.DOCUMENTS_FLOW)) {
            new IHSM_FullDocumentsFlowTest().verifyFullDocumentsInformation();
            FlowStateUtils.saveStep(FlowStep.DOCUMENTS_FLOW);
        }

        logger.info("===== FULL EMPLOYEE REGISTRATION FLOW COMPLETED SUCCESSFULLY =====");
    }

    /* ================= FLOW CONTROL ================= */

    private boolean shouldRun(FlowStep last, FlowStep current) {
        return last == null || last.ordinal() < current.ordinal();
    }
}
