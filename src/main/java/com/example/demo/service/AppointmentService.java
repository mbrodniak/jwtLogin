package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.*;

@Service
public class AppointmentService {

    private final Integer monday = Calendar.MONDAY;
    private final Integer friday = Calendar.FRIDAY;
    private final Integer startHour = 6;
    private final Integer endHour = 23;
    private final Integer minute = 0;

//    private final org.apache.logging.log4j.Logger log = (Logger) java.util.logging.Logger.getLogger(getClass().getName());


    @Autowired
    AppointmentRepository appointmentRepository;

    public List<String> getAppointments(String date, int id){

        Timestamp startDate =  getAppointmentsDate(date, startHour, minute);
        Timestamp endDate = getAppointmentsDate(date, endHour, minute);
        Calendar calendar = Calendar.getInstance();
        List<String> appointmentHours = new LinkedList<>();


        List<Appointment> byDateBetween = appointmentRepository.findByDateBetweenAndDoctorList(startDate, endDate, id);
        for (Appointment appointment: byDateBetween){
            calendar.setTimeInMillis(appointment.getDate().getTime() - 7200000);
            String[] splitted = calendar.getTime().toString().split(" ")[3].split(":");
            String hour = splitted[0] + ":" + splitted[1];
            appointmentHours.add(hour);
        }
        return appointmentHours;
    }

    public boolean insertAppointment(Timestamp date){

        try {
            Appointment appointment = new Appointment();
            appointment.setDate(date);
            appointmentRepository.save(appointment);
            return true;
        }
        catch (Exception e){
//            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }

        return false;
    }
//
//    public Timestamp getDate(String week, int day, int hour, int minute){
//
//        int year = Integer.parseInt(week.split("-")[0]);
//        int weekNumber =  Integer.parseInt(week.split("W")[1]);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
//
//        calendar.set(Calendar.DAY_OF_WEEK, day);
//        calendar.set(Calendar.HOUR_OF_DAY, hour);
//        calendar.set(Calendar.MINUTE, minute);
//        return new Timestamp(calendar.getTime().getTime());
//    }
//    public int getYear(String week){
//        return Integer.parseInt(week.split("-")[0]);
//    }
//    public int getMonth(String week){
//        int year = Integer.parseInt(week.split("-")[0]);
//        int weekNumber =  Integer.parseInt(week.split("W")[1]);
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.WEEK_OF_YEAR, weekNumber);
//        int month =( calendar.get(Calendar.MONTH) + 1);
//        return month;
//    }

    public Timestamp getAppointmentsDate(String date, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
//        System.out.println(date1);
        String[] splitted = date.split("-");
        int day = Integer.parseInt(splitted[2]);
        int month = Integer.parseInt(splitted[1]);
        int year = Integer.parseInt(splitted[0]);
        calendar.set(year, (month-1), day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        return new Timestamp(calendar.getTime().getTime());
    }

}
