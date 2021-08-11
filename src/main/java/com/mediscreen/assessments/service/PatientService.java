package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Patient;


import java.util.List;

public interface PatientService {

    /**
     *
     * @param patientId patient Id
     * @return patient found
     */
    Patient getPatientById (Integer patientId);

    /**
     *
     * @param familyName name of family
     * @return List of patient by family name
     */
    List<Patient> getPatientByFamilyName(String familyName);
}
