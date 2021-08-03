package com.mediscreen.assessments.service;

import com.mediscreen.assessments.model.Assessment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AssessmentService {

    public Assessment getAssessmentByPatient (Integer patientId);

    public List<Assessment> getAssessmentByFamilyName(String familyName);

}
