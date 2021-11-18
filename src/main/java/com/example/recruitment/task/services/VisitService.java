package com.example.recruitment.task.services;

import com.example.recruitment.task.Model.DTOs.VisitDTO;
import com.example.recruitment.task.Model.entities.Patient;
import com.example.recruitment.task.Model.entities.Practitioner;
import com.example.recruitment.task.Model.entities.Visit;
import com.example.recruitment.task.repos.UsedRepos.PatientsRepository;
import com.example.recruitment.task.repos.UsedRepos.PractitionersRepository;
import com.example.recruitment.task.repos.UsedRepos.VisitsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private final VisitsRepository visitsRepository;
    private final PractitionersRepository practitionersRepository;
    private final PatientsRepository patientsRepository;

    public VisitService(VisitsRepository visitsRepository, PractitionersRepository practitionersRepository, PatientsRepository patientsRepository) {
        this.visitsRepository = visitsRepository;
        this.practitionersRepository = practitionersRepository;
        this.patientsRepository = patientsRepository;
    }

    public ResponseEntity<?> getVisitsCountBySpecialization(List<String> specialization, List<String> cities) {
        VisitDTO build = new VisitDTO();
        StringBuilder buildSpec = new StringBuilder();
        long counter = 0L;
        long counter1 = 0L;
        if (specialization.get(0).equals("ALL")) {
            build.setVisit("ALL");
            build.setNumberOfVists((long) visitsRepository.findAll().size());
            build.setPatientsNumberOfVists((long) visitsRepository.findAll().size());
        } else {
            for (String s : specialization) {
                if (!practitionersRepository.findBySpecialization(s).isPresent()) {
                    return ResponseEntity.badRequest().body("Brak specjalizacji");
                } else {
                    Practitioner practitioner = practitionersRepository.findBySpecialization(s).orElseThrow(EntityNotFoundException::new);
                    List<Visit> collect = visitsRepository.findAll().stream().filter(f -> f.getPractitionerId().equals(practitioner.getId())).collect(Collectors.toList());
                    buildSpec.append(" ").append(practitioner.getSpecialization());
                    counter = counter + collect.size();
                    build.setVisit(String.valueOf(buildSpec));
                    build.setNumberOfVists(counter);
                    if (cities.size() > 0) {
                        for (String city : cities) {
                            List<Patient> allByCity = patientsRepository.findAllByCity(city);
                            List<Patient> patients = new ArrayList<>();
                            for (int k = 0; k < allByCity.size(); k++) {
                                int finalK = k;
                                if (patients.stream().noneMatch(n -> n.getId().equals(allByCity.get(finalK).getId()))) {
                                    patients.add(allByCity.get(finalK));
                                }

                            }
                            for (int k = 0; k < patients.size(); k++) {
                                List<Practitioner> practitioner1 = patients.get(k).getPractitioner();
                                for (Practitioner value : practitioner1) {
                                    if (value.getSpecialization().equals(s)) {
                                        Long id = value.getId();
                                        int finalK = k;
                                        long count = visitsRepository.findAll()
                                                .stream()
                                                .filter(f -> f.getPractitionerId().equals(id) && f.getPatientId().equals(patients.get(finalK).getId()))
                                                .count();
                                        counter1 += count;
                                    }
                                }
                            }
                        }
                        build.setPatientsNumberOfVists(counter1);
                    }
                }
            }

        }
        return ResponseEntity.ok(build);
    }

}
