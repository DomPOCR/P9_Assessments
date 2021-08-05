package com.mediscreen.assessments.UT;

import com.mediscreen.assessments.dto.Note;
import com.mediscreen.assessments.dto.Patient;
import com.mediscreen.assessments.model.Assessment;
import com.mediscreen.assessments.proxies.NoteProxy;
import com.mediscreen.assessments.proxies.PatientProxy;
import com.mediscreen.assessments.service.AssessmentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AssessmentServiceTest {

    @Autowired
    private AssessmentService assessmentService;

    @MockBean
    private PatientProxy patientProxy;

    @MockBean
    private NoteProxy noteProxy;

    // Constantes pour le jeu de test
    private Assessment assessmentTest;

    Integer patientId = 5;
    Integer patientIdUnknown = 0;
    String patientFirstName = "Jack";
    String patientLastName = "Bauer";
    String patientLastNameUnknown = "Bond";
    String patientAdress = "02 rue untelle";
    String patientPhone = "00-00";

    String noteId = "1";

    @Test
    public void getAssessmentByPatientId_UnknownPatientId_Test() {

        //GIVEN
        Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(null);

        //WHEN
        Assessment assessmentResult = assessmentService.getAssessmentByPatientId(patientIdUnknown);

        //THEN
        assertThat(assessmentResult).isNull();

    }

   @Test
    public void getAssessmentByPatientId_None_Test() {

       //GIVEN
       LocalDate patientBirthDate = LocalDate.now().minusYears(20L);
       String patientGenre = "M";
       Patient patientTest = new Patient(patientId,patientFirstName,patientLastName,patientAdress, patientBirthDate,patientPhone,patientGenre);

       String noteText = "Taille";
       Note noteTest = new Note(noteId,noteText,patientId,LocalDate.now());
       List<Note> noteListTest = new ArrayList<>();
       noteListTest.add(noteTest);

       //WHEN
       Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(patientTest);
       Mockito.when(noteProxy.getPatientNoteByPatientId(anyInt())).thenReturn(noteListTest);

       //THEN
       assertThat(assessmentService.getAssessmentByPatientId(patientId).getAssessmentLevel()).isEqualTo("None");

   }

    @Test
    public void getAssessmentByPatientId_BorderLine_Test() {

        //GIVEN
        LocalDate patientBirthDate = LocalDate.now().minusYears(40L);
        String patientGenre = "M";
        Patient patientTest = new Patient(patientId,patientFirstName,patientLastName,patientAdress, patientBirthDate,patientPhone,patientGenre);

        String noteText = "Hémoglobine A1C, Microkatal, Taille, Poids";
        Note noteTest = new Note(noteId,noteText,patientId,LocalDate.now());
        List<Note> noteList = new ArrayList<>();
        noteList.add(noteTest);

        //WHEN
        Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(patientTest);
        Mockito.when(noteProxy.getPatientNoteByPatientId(anyInt())).thenReturn(noteList);

        //THEN
        assertThat(assessmentService.getAssessmentByPatientId(patientId).getAssessmentLevel()).isEqualTo("Borderline");

    }

    @Test
    public void getAssessmentByPatientId_In_Danger_Test() {

        //GIVEN
        LocalDate patientBirthDate = LocalDate.now().minusYears(30L);
        String patientGenre = "F";
        Patient patientTest = new Patient(patientId,patientFirstName,patientLastName,patientAdress, patientBirthDate,patientPhone,patientGenre);

        String noteText = "Hémoglobine A1C, Microkatal, Taille, Poids, Fumeur, Anormal";
        Note noteTest = new Note(noteId,noteText,patientId,LocalDate.now());
        List<Note> noteList = new ArrayList<>();
        noteList.add(noteTest);

        //WHEN
        Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(patientTest);
        Mockito.when(noteProxy.getPatientNoteByPatientId(anyInt())).thenReturn(noteList);

        //THEN
        assertThat(assessmentService.getAssessmentByPatientId(patientId).getAssessmentLevel()).isEqualTo("In Danger");

    }

    @Test
    public void getAssessmentByPatientId_Early_onset_Test() {

        //GIVEN
        LocalDate patientBirthDate = LocalDate.now().minusYears(25L);
        String patientGenre = "M";
        Patient patientTest = new Patient(patientId,patientFirstName,patientLastName,patientAdress, patientBirthDate,patientPhone,patientGenre);

        String noteText = "Hémoglobine A1C, Microkatal, Taille, Poids, Fumeur, Anormal, Cholestérol, Vertige";
        Note noteTest = new Note(noteId,noteText,patientId,LocalDate.now());
        List<Note> noteList = new ArrayList<>();
        noteList.add(noteTest);

        //WHEN
        Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(patientTest);
        Mockito.when(noteProxy.getPatientNoteByPatientId(anyInt())).thenReturn(noteList);

        //THEN
        assertThat(assessmentService.getAssessmentByPatientId(patientId).getAssessmentLevel()).isEqualTo("Early onset");

    }

    @Test
    public void getAssessmentByFamilyName_None_Test() {

        //GIVEN
        LocalDate patientBirthDate = LocalDate.now().minusYears(20L);
        String patientGenre = "M";
        Patient patientTest = new Patient(patientId,patientFirstName,patientLastName,patientAdress, patientBirthDate,patientPhone,patientGenre);
        List<Patient> patientListTest = new ArrayList<>();
        patientListTest.add(patientTest);

        String noteText = "Taille";
        Note noteTest = new Note(noteId,noteText,patientId,LocalDate.now());
        List<Note> noteListTest = new ArrayList<>();
        noteListTest.add(noteTest);

        //WHEN
        Mockito.when(patientProxy.getPatientById(anyInt())).thenReturn(patientTest);
        Mockito.when(patientProxy.getPatientByFamilyName(anyString())).thenReturn(patientListTest);
        Mockito.when(noteProxy.getPatientNoteByPatientId(anyInt())).thenReturn(noteListTest);

        //THEN
        assertThat(assessmentService.getAssessmentByFamilyName(patientLastName).get(0).getAssessmentLevel()).isEqualTo("None");
    }
}
