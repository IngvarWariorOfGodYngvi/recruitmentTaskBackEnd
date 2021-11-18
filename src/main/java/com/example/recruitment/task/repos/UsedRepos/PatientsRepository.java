package com.example.recruitment.task.repos.UsedRepos;

import com.example.recruitment.task.Model.entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientsRepository {

    List<Patient> findAll();

    Optional<Patient> findById(Long id);

    Patient save(Patient entity);

    List<Patient> findAllByCity(String city);

    List<String> getDistinctByCity();
}
