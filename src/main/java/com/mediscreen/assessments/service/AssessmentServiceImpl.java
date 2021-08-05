package com.mediscreen.assessments.service;

import com.mediscreen.assessments.dto.Note;
import com.mediscreen.assessments.dto.Patient;
import com.mediscreen.assessments.model.Assessment;
import com.mediscreen.assessments.web.exception.AssessmentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    // Pour le log4j2
    final Logger logger = LogManager.getLogger(this.getClass().getName());

    private final PatientService patientService;
    private final NoteService noteService;

    @Autowired
    public AssessmentServiceImpl(PatientService patientService, NoteService noteService) {
        this.patientService = patientService;
        this.noteService = noteService;
    }

    private List<String> triggersList = Arrays.asList("Hémoglobine A1C", "Microkatal", "Taille", "Poids", "Fumeur", "Anormal", "Cholestérol", "Vertige", "Rechute", "Réaction", "Anticorps");


    @Override
    public Assessment getAssessmentByPatientId(Integer patientId) {

        Assessment assessment = new Assessment();
        int nbOfTriggers = 0;

        Patient patient = patientService.getPatientById(patientId);
        if (patient != null) {

            List<Note> noteList = noteService.getPatientNoteByPatientId(patientId);

            assessment.setPatientId(patientId);
            assessment.setPatientFirstName(patient.getFirstName());
            assessment.setPatientLastName(patient.getLastName());
            assessment.setPatientAge(Period.between(patient.getBirthdate(), LocalDate.now()).getYears());
            assessment.setPatientSex(patient.getGenre());

            for (Note n : noteList) {
                nbOfTriggers = nbOfTriggers + searchTriggers(n);
            }
            assessment.setAssessmentNbOfTrigger(nbOfTriggers);

            assessment.setAssessmentLevel(calculateLevelOfAssessment(assessment.getPatientAge(), assessment.getPatientSex(), assessment.getAssessmentNbOfTrigger()));
            return assessment;
        }
        else return null;

    }

    @Override
    public List<Assessment> getAssessmentByFamilyName(String familyName) {


        return null;
    }


    public int searchTriggers(Note note) {
        int numberOfTriggers = 0;

        for (String w : triggersList) {
            if (note.getTextNote().toLowerCase().contains(w.toLowerCase())) {
                numberOfTriggers += 1;
            }
        }
        return numberOfTriggers;
    }

    public String calculateLevelOfAssessment(int age, String sex, int nbOfTrigger){

        String level = "None";

        if (nbOfTrigger < 2) {
            level = "None";
        } else if (nbOfTrigger < 6 && age > 30) {
            level = "Borderline";
        } else if (nbOfTrigger < 3 && age <= 30) {
            level = "Borderline";
        } else if (nbOfTrigger < 4 && age <= 30) {
            level = "Borderline";
        } else if (nbOfTrigger < 5 && age <= 30 && sex.contains("M")) {
            level = "In Danger";
        } else if (nbOfTrigger < 7 && age <= 30 && sex.contains("F")) {
            level = "In Danger";
        } else if (nbOfTrigger < 8 && age > 30) {
            level = "In Danger";
        } else {
            level = "Early onset";
        }

        return level;
    }
}
