package com.example.demo.service;

import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor findById(int id){
        return doctorRepository.findById(id);
    }
}
