package com.mediscreen.assessments.model;

public class Assessment {

    private Integer patientId;
    private String assessmentLevel;

    public Assessment() {
    }

    public Assessment(Integer patientId, String assessmentLevel) {
        this.patientId = patientId;
        this.assessmentLevel = assessmentLevel;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "patientId=" + patientId +
                ", assessmentLevel='" + assessmentLevel + '\'' +
                '}';
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
