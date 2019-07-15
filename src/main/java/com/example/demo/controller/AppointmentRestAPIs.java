package com.example.demo.controller;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping(path = "api/appointment")
@CrossOrigin("*")
public class AppointmentRestAPIs {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(path = "/date")
    public List<String> getAppointments(@RequestParam String date, @RequestParam String id){

        int doctor_id = Integer.parseInt(id);
        return appointmentService.getAppointments(date, doctor_id);

//        return new ScheduleResponse(appointmentService.getDaysInWeek(week), appointmentService.getAppointments(week),
//                appointmentService.getMonth(week), appointmentService.getYear(week));
    }

}
