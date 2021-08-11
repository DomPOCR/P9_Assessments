package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Note;

import java.util.List;

public interface NoteService {

    /**
     *
     * @param patientId patient id
     * @return List of Patient's Notes
     */
    List<Note> getPatientNoteByPatientId(Integer patientId);
}
