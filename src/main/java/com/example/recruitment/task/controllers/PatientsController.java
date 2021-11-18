package com.example.recruitment.task.controllers;

import com.example.recruitment.task.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
@CrossOrigin
public class PatientsController {

    private final PatientService patientService;

    public PatientsController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/getByCitiesOrSpecialities")
    public ResponseEntity<?> getPatientsByCitiesOrSpecialities(@RequestParam @Nullable List<String> cities, @RequestParam @Nullable List<String> specialities) {

        if (cities == null || cities.isEmpty()) {
            cities = new ArrayList<>();
            cities.add("ALL");
        }
        if (specialities == null || specialities.isEmpty()) {
            specialities = new ArrayList<>();
            specialities.add("ALL");
        }

        return patientService.getAllPatientsByCityOrSpecialities(cities, specialities);
    }
}
