package com.example.recruitment.task.repos.UsedRepos;

import com.example.recruitment.task.Model.entities.Practitioner;

import java.util.List;
import java.util.Optional;

public interface PractitionersRepository {

    List<Practitioner> findAll();

    Optional<Practitioner> findById(Long id);

    Practitioner save(Practitioner entity);

    Practitioner getById(Long id);

    Optional<Practitioner> findBySpecialization(String specialization);

    List<String> getDistinctBySpecialization();
}
