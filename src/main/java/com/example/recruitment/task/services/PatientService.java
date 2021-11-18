package com.example.recruitment.task.services;

import com.example.recruitment.task.Model.DTOs.PatientDTO;
import com.example.recruitment.task.Model.entities.Patient;
import com.example.recruitment.task.repos.UsedRepos.PatientsRepository;
import com.example.recruitment.task.repos.UsedRepos.PractitionersRepository;
import com.example.recruitment.task.repos.UsedRepos.VisitsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientsRepository patientsRepository;
    private final VisitsRepository visitsRepository;
    private final PractitionersRepository practitionersRepository;

    public PatientService(PatientsRepository patientsRepository, VisitsRepository visitsRepository, PractitionersRepository practitionersRepository) {
        this.patientsRepository = patientsRepository;
        this.visitsRepository = visitsRepository;
        this.practitionersRepository = practitionersRepository;
    }

    public ResponseEntity<?> getAllPatientsByCityOrSpecialities(List<String> cities, List<String> specialities) {
        List<Patient> patients = new ArrayList<>();
        List<PatientDTO> patientDTOS = new ArrayList<>();

        if (cities.get(0).equals("ALL")) {
            patients = patientsRepository.findAll();
        } else {
            for (int i = 0; i < cities.size(); i++) {
                patients.addAll(patientsRepository.findAllByCity(cities.get(i)));
            }
        }

        if (specialities.get(0).equals("ALL")) {
            for (int i = 0; i < patients.size(); i++) {
                int finalI = i;
                List<Patient> finalPatients = patients;
                long count = visitsRepository.findAll().stream().filter(f -> f.getPatientId().equals(finalPatients.get(finalI).getId())).count();
                patientDTOS.add(PatientDTO.builder()
                        .firstName(patients.get(i).getFirstName())
                        .lastName(patients.get(i).getLastName())
                        .countVisits(count).build());
            }
        } else {
            for (int i = 0; i < patients.size(); i++) {
                long counter = 0L;
                for (String s : specialities) {
                    Long id = practitionersRepository.findBySpecialization(s).orElseThrow(EntityNotFoundException::new).getId();

                    int finalI = i;
                    List<Patient> finalPatients1 = patients;
                    long count = visitsRepository.findAll()
                            .stream()
                            .filter(f -> f.getPractitionerId().equals(id) && f.getPatientId().equals(finalPatients1.get(finalI).getId()))
                            .count();
                    counter += count;
                }
                patientDTOS.add(PatientDTO.builder()
                        .firstName(patients.get(i).getFirstName())
                        .lastName(patients.get(i).getLastName())
                        .countVisits(counter).build());
            }
        }
        return ResponseEntity.ok(patientDTOS);
    }


}
