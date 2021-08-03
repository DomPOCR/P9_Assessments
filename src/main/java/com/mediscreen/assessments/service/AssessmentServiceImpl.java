package com.mediscreen.assessments.service;

import com.mediscreen.assessments.model.Assessment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {


    @Override
    public Assessment getAssessmentByPatient(Integer patientId) {
        return null;
    }

    @Override
    public List<Assessment> getAssessmentByFamilyName(String familyName) {
        return null;
    }
}
