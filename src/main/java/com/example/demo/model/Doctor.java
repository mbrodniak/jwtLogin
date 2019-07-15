package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.LinkedList;

@Entity(name = "doctor")
public class Doctor {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "spec")
    private String specialization;

    @JsonIgnore()
    @ManyToMany(mappedBy = "doctorList")
    private List<Appointment> appointmentList = new LinkedList<>();

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
