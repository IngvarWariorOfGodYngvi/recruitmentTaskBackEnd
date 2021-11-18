package com.example.recruitment.task.repos.SQLrepos;

import com.example.recruitment.task.Model.entities.Patient;
import com.example.recruitment.task.repos.UsedRepos.PatientsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientsSQLRepository extends PatientsRepository, JpaRepository<Patient, Long> {

    List<Patient> findAllByCity(String city);

    @Override
    @Query("select distinct city from Patient")
    List<String> getDistinctByCity();
}
