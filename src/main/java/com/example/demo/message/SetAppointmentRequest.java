package com.example.demo.message;

import java.sql.Timestamp;

public class SetAppointmentRequest {

    private Timestamp date;
    private String doctorId;
    private String patientId;

    public SetAppointmentRequest() {
    }

    public SetAppointmentRequest(Timestamp date, String doctorId, String patientId) {
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "SetAppointmentRequest{" +
                "date=" + date +
                ", doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}
