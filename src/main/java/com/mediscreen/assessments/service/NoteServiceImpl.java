package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Note;
import com.mediscreen.assessments.proxies.NoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteProxy noteProxy;

    public NoteServiceImpl(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    @Override
    public List<Note> getPatientNoteByPatientId(Integer patientId) {
        return noteProxy.getPatientNoteByPatientId(patientId);
    }
}
