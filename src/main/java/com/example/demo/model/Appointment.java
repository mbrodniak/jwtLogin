package com.example.demo.model;

import java.util.List;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;

@Entity(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date")
    private Timestamp date;

//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_appointment",
//            joinColumns = @JoinColumn(name = "appointment_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> userList = new LinkedList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "doctor_appointment",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id"))
    private List<Doctor> doctorList = new LinkedList<>();



    public Long getId() {
        return id;
    }

    public void setId(Long appointmentId) {
        this.id = appointmentId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public List<User> getUserList() {
//        return userList;
//    }

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
}
