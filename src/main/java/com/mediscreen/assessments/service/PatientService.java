package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Patient;


import java.util.List;

public interface PatientService {

    Patient getPatientById (Integer patientId);
    List<Patient> getPatientByFamilyName(String familyName);
}
