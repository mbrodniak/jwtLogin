package com.example.demo.repository;

import com.example.demo.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    @Query(value = "select * from doctor", nativeQuery = true)
    List<Doctor> findAll();

    Doctor findById(int id);

}
