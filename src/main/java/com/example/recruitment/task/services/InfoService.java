package com.example.recruitment.task.services;

import com.example.recruitment.task.repos.UsedRepos.PatientsRepository;
import com.example.recruitment.task.repos.UsedRepos.PractitionersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {

    private final PatientsRepository patientsRepository;
    private final PractitionersRepository practitionersRepository;

    public InfoService(PatientsRepository patientsRepository, PractitionersRepository practitionersRepository) {
        this.patientsRepository = patientsRepository;
        this.practitionersRepository = practitionersRepository;
    }

    public List<String> getAllCities() {
        return patientsRepository.getDistinctByCity();
    }

    public List<String> getAllSpecializations() {
        return practitionersRepository.getDistinctBySpecialization();
    }
}
