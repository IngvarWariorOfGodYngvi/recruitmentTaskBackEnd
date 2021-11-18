package com.example.recruitment.task.controllers;

import com.example.recruitment.task.services.InfoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/info")
@CrossOrigin
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/cities")
    public List<String> getAllCities(){
        return infoService.getAllCities();
    }

    @GetMapping("/specializations")
    public List<String> getAllSpecializations() {
        return infoService.getAllSpecializations();
    }
}
