package com.mediscreen.assessments.web.controller;

import com.mediscreen.assessments.model.Assessment;
import com.mediscreen.assessments.service.AssessmentService;
import com.mediscreen.assessments.web.exception.AssessmentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "assess/{id}")
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
        logger.info("Assessment OK for patient id : " +patientId);
        return assessmentResult;
    }

}
