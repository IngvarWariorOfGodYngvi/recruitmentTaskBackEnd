package com.example.recruitment.task.repos.SQLrepos;

import com.example.recruitment.task.Model.entities.Visit;
import com.example.recruitment.task.repos.UsedRepos.VisitsRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitsSQLRepository extends VisitsRepository, JpaRepository<Visit, Long> {

}
