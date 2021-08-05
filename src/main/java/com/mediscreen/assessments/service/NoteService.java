package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Note;

import java.util.List;

public interface NoteService {

    List<Note> getPatientNoteByPatientId(Integer patientId);
}
