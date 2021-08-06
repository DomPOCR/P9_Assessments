package com.mediscreen.assessments.proxies;

import com.mediscreen.assessments.dto.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "note", url = "http://note:8082")
public interface NoteProxy {

    @GetMapping(value = "/patHistory/patient/{patientId}")
    List<Note> getPatientNoteByPatientId(@PathVariable("patientId") Integer patientId);
}
