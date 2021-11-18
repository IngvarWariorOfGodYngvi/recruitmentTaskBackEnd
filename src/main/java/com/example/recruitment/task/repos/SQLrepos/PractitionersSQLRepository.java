package com.example.recruitment.task.repos.SQLrepos;

import com.example.recruitment.task.Model.entities.Practitioner;
import com.example.recruitment.task.repos.UsedRepos.PractitionersRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PractitionersSQLRepository extends PractitionersRepository, JpaRepository<Practitioner,Long> {

    Optional<Practitioner> findBySpecialization(String specialization);
    @Query("select distinct specialization from Practitioner")
    List<String> getDistinctBySpecialization();
}
