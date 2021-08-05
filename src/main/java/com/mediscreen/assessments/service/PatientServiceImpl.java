package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Patient;
import com.mediscreen.assessments.proxies.PatientProxy;
import feign.FeignException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientServiceImpl implements PatientService {

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());


    private final PatientProxy patientProxy;

    @Autowired
    public PatientServiceImpl(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @Override
    public Patient getPatientById(Integer patientId)  {

        Patient patientResult;

        try {
            patientResult = patientProxy.getPatientById(patientId);
        } catch (FeignException e) {
            logger.error("error on patient proxy : " + e.getMessage());
            return null;
        }
        return patientResult;
    }

    @Override
    public List<Patient> getPatientByFamilyName(String familyName) {

        List<Patient> patientResult;
        try {
            patientResult = patientProxy.getPatientByFamilyName(familyName);
        } catch (FeignException e) {
            logger.error("error on patient proxy : " + e.getMessage());
            return null;
        }
        return patientResult;
    }
}
