package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Override
    List<Appointment> findAll();

    @Query(value = "select * from appointment where timestamp(date) between ?1 and ?2 and id in (select appointment_id from doctor_appointment where doctor_id = ?3)" ,nativeQuery = true)
    List<Appointment> findByDateBetweenAndDoctorList(Timestamp startDate, Timestamp endDate, int doctor_id);

}
