package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Patient;
import com.mediscreen.assessments.proxies.PatientProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientProxy patientProxy;

    @Autowired
    public PatientServiceImpl(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @Override
    public Patient getPatientById(Integer patientId)    {

        return patientProxy.getPatientById(patientId);
    }

    @Override
    public List<Patient> getPatientByFamilyName(String familyName) {
        return patientProxy.getPatientByFamilyName(familyName);
    }
}
