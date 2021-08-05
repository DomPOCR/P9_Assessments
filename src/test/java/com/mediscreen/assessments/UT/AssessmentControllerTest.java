package com.mediscreen.assessments.UT;

import com.mediscreen.assessments.model.Assessment;
import com.mediscreen.assessments.service.AssessmentService;
import com.mediscreen.assessments.web.controller.AssessmentController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssessmentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    // Constantes pour le jeu de test
    private Assessment assessmentTest;

    Integer patientId = 5;
    String patientFirstName = "Jack";
    String patientLastName = "Bauer";
    String patientLastNameUnknown = "Bond";
    int patientAge = 35;
    String patientSex = "M";
    Integer assessmentNbOfTrigger = 1;
    String assessmentLevel = "None";

    @BeforeEach
    public void setUpEach() {
        assessmentTest = new Assessment(patientId, patientFirstName, patientLastName, patientAge, patientSex, assessmentNbOfTrigger, assessmentLevel);
    }

    @Test
    public void getAssessmentByPatientIdTest() throws Exception {

        //GIVEN
        Mockito.when(assessmentService.getAssessmentByPatientId(anyInt())).thenReturn(assessmentTest);

        //WHEN THEN
        mockMvc.perform(get("/assessment/" + assessmentTest.getPatientId())
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpect(status().isOk());
    }

    @Test
    public void getAssessmentByPatientId_NotFoundTest() throws Exception {

        //GIVEN
        Mockito.when(assessmentService.getAssessmentByPatientId(anyInt())).thenReturn(null);

        //WHEN THEN
        try {
            mockMvc.perform(get("/assessment/99")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Patient id 99 not found"));
        }
    }

    @Test
    public void getAssessmentByFamilyNameTest() throws Exception {

        //GIVEN
        List<Assessment> assessmentListTest = new ArrayList<>();
        assessmentListTest.add(assessmentTest);
        Mockito.when(assessmentService.getAssessmentByFamilyName(any(String.class))).thenReturn(assessmentListTest);

        //WHEN THEN
        mockMvc.perform(get("/assessment/familyname/" + assessmentTest.getPatientLastName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAssessmentByFamilyName_NotFoundTest() throws Exception {

        //GIVEN
        Mockito.when(assessmentService.getAssessmentByPatientId(anyInt())).thenReturn(null);

        //WHEN THEN
        try {
            mockMvc.perform(get("/assessment/familyname/" + patientLastNameUnknown)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andDo(print());
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Patients with family name : " + patientLastNameUnknown + " not found"));
        }
    }
}
