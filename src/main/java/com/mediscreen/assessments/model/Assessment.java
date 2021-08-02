package com.mediscreen.assessments.model;

public class Assessment {

    private Integer id;
    private Integer patientId;
    private String assessmentLevel;

    public Assessment() {
    }

    public Assessment(Integer id, Integer patientId, String assessmentLevel) {
        this.id = id;
        this.patientId = patientId;
        this.assessmentLevel = assessmentLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getAssessmentLevel() {
        return assessmentLevel;
    }

    public void setAssessmentLevel(String assessmentLevel) {
        this.assessmentLevel = assessmentLevel;
    }
}
