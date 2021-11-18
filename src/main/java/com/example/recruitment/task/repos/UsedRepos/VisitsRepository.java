package com.example.recruitment.task.repos.UsedRepos;

import com.example.recruitment.task.Model.entities.Visit;

import java.util.List;
import java.util.Optional;

public interface VisitsRepository {

    List<Visit> findAll();

    Optional<Visit> findById(Long id);

    Visit save(Visit entity);
}
