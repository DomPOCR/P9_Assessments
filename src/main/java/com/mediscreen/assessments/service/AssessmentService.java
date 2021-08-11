package com.mediscreen.assessments.service;

import com.mediscreen.assessments.model.Assessment;

import java.util.List;

public interface AssessmentService {

    /**
     *
     * @param patientId patient id
     * @return assessment for patient
     */
    Assessment getAssessmentByPatientId(Integer patientId);

    /**
     *
     * @param familyName name of family
     * @return list of assessments by family name
     */
    List<Assessment> getAssessmentByFamilyName(String familyName);

}
