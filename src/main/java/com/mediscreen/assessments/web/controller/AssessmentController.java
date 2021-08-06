package com.mediscreen.assessments.web.controller;

import com.mediscreen.assessments.model.Assessment;
import com.mediscreen.assessments.service.AssessmentService;
import com.mediscreen.assessments.web.exception.AssessmentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AssessmentController {

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final AssessmentService assessmentService;

    @Autowired
    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping(value = "assessment/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Assessment getAssessmentByPatientId(@PathVariable("id") Integer patientId) throws AssessmentException {

        Assessment assessmentResult = null;
        String eMess = null;
        assessmentResult = assessmentService.getAssessmentByPatientId(patientId);
        if (assessmentResult == null) {
            eMess = "Patient id " + patientId + " not found";
            logger.error(eMess);
            throw new AssessmentException(eMess);
        }
        logger.info("Assessment OK for patient id : " + patientId);
        return assessmentResult;
    }

    @GetMapping(value = "assessment/familyname/{familyName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Assessment> getAssessmentsByFamilyName(@PathVariable("familyName") String familyName) throws AssessmentException {

        List<Assessment> assessmentResult = null;
        String eMess = null;

        assessmentResult = assessmentService.getAssessmentByFamilyName(familyName);
        if ((assessmentResult == null) || (assessmentResult.size() == 0)) {
            eMess = "Patients with family name : " + familyName + " not found";
            logger.error(eMess);
            throw new AssessmentException(eMess);
        }
        logger.info("Assessment OK for patient with family name : " + familyName);
        return assessmentResult;
    }

}
