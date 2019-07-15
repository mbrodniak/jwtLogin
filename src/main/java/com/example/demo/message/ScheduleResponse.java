package com.example.demo.message;

import com.example.demo.model.Appointment;

import java.util.LinkedList;
import java.util.List;

public class ScheduleResponse {

    private int month;
    private int year;
    private List<Integer> days = new LinkedList<>();
    private List<Appointment> appointments = new LinkedList<>();


    public ScheduleResponse(List<Integer> days, List<Appointment> appointments, int month, int year){
        this.days = days;
        this.appointments = appointments;
        this.month = month;
        this.year = year;
    }

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
