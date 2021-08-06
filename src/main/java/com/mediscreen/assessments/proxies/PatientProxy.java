package com.mediscreen.assessments.proxies;

import com.mediscreen.assessments.dto.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "patient", url = "http://patient:8080")
public interface PatientProxy {

    @GetMapping(value = "patient/{id}")
    Patient getPatientById(@PathVariable Integer id);

    @GetMapping(value = "/patient/familyname/{familyName}")
    List<Patient> getPatientByFamilyName(@PathVariable String familyName);

}
