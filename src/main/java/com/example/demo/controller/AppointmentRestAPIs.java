package com.example.demo.controller;
import com.example.demo.message.SetAppointmentRequest;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(path = "api/appointment")
@CrossOrigin("*")
public class AppointmentRestAPIs {

    private final static long twoHours = 7200000;

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(path = "/date")
    public List<String> getAppointments(@RequestParam(name = "date") String date, @RequestParam(name = "id") String id){

        int doctor_id = Integer.parseInt(id);
        return appointmentService.getAppointments(date, doctor_id);

    }

    @PostMapping(path = "/setAppointment")
    public boolean setAppointment(@RequestBody SetAppointmentRequest appointmentRequest){

        long time = (appointmentRequest.getDate().getTime() - twoHours);
        if(this.appointmentService.insertAppointment(new Timestamp(time))){
            return true;
        }
        else {
            return false;
        }
    }

}
