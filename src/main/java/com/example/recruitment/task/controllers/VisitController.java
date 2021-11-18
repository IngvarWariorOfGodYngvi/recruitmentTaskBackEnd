package com.example.recruitment.task.controllers;

import com.example.recruitment.task.services.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/visit")
@CrossOrigin
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/getVisitsBySpecialization")
    public ResponseEntity<?> getVisitsBySpecialization(@RequestParam @Nullable List<String> cities, @RequestParam List<String> specialization) {
        if (specialization == null || specialization.isEmpty()) {
            specialization = new ArrayList<>();
            specialization.add("ALL");
        }
        if (cities == null || cities.isEmpty()) {
            cities = new ArrayList<>();
        }
        return visitService.getVisitsCountBySpecialization(specialization,cities);
    }
}
