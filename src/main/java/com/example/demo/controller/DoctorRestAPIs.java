package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.model.Doctor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.*;

@RestController
@RequestMapping(path = "/api/doctor")
@CrossOrigin("*")
public class DoctorRestAPIs {

    @Autowired
    DoctorService doctorService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @GetMapping(path = "/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        return  ResponseEntity.ok(doctorService.findAll());
    }

    @GetMapping(path = "/id")
    public ResponseEntity<Doctor> getDoctorById(@RequestParam int id){
        return ResponseEntity.ok(doctorService.findById(id));
    }

    @GetMapping(path = "appointment/all") 
    public List<Appointment> appointments() {
        return appointmentRepository.findAll();
    }





}
