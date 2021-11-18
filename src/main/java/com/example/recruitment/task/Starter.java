package com.example.recruitment.task;

import com.example.recruitment.task.Model.entities.Patient;
import com.example.recruitment.task.Model.entities.Practitioner;
import com.example.recruitment.task.Model.entities.Visit;
import com.example.recruitment.task.repos.UsedRepos.PatientsRepository;
import com.example.recruitment.task.repos.UsedRepos.PractitionersRepository;
import com.example.recruitment.task.repos.UsedRepos.VisitsRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.io.*;
import java.util.List;

@Component
public class Starter implements ApplicationListener<ContextRefreshedEvent> {

    private final PatientsRepository patientRepository;
    private final PractitionersRepository practitionersRepository;
    private final VisitsRepository visitsRepository;

    public Starter(PatientsRepository patientRepository, PractitionersRepository practitionersRepository, VisitsRepository visitsRepository) {
        this.patientRepository = patientRepository;
        this.practitionersRepository = practitionersRepository;
        this.visitsRepository = visitsRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addPatients();
        addPractitioners();
        addVisits();
        addPatient2Practitioner();
    }

    public void addPatients() {
        String file = "./src/main/resources/patients.csv";
        String line = "";
        try {
        FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.replaceAll("\"", "").split(",");
                if (!row[0].equals("")) {
                    Patient patient = new Patient();
                    patient.setId(Long.valueOf(row[0]));
                    patient.setFirstName(row[1]);
                    patient.setLastName(row[2]);
                    patient.setCity(row[3]);
                    patient.setCreatedAt(row[4]);
                    patientRepository.save(patient);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPractitioners() {
        String file = "./src/main/resources/practitioners.csv";
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.replaceAll("\"", "").split(",");
                if (!row[0].equals("")) {
                    Practitioner practitioner = new Practitioner();
                    practitioner.setId(Long.valueOf(row[0]));
                    practitioner.setSpecialization(row[1]);

                    practitionersRepository.save(practitioner);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addVisits() {
        String file = "./src/main/resources/visits.csv";
        String line = "";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.replaceAll("\"", "").split(",");
                if (!row[0].equals("")) {
                    Visit visit = new Visit();
                    visit.setId(Long.valueOf(row[0]));
                    visit.setPractitionerId(Long.valueOf(row[1]));
                    visit.setPatientId(Long.valueOf(row[2]));
                    visitsRepository.save(visit);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPatient2Practitioner() {
        String file = "./src/main/resources/patient2practitioner.csv";
        String line = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] row = line.replaceAll("\"", "").split(",");
                if (!row[0].equals("")) {
                    Patient patient = patientRepository.findById(Long.valueOf(row[0])).orElseThrow(EntityNotFoundException::new);
                    Practitioner practitioner = practitionersRepository.getById(Long.valueOf(row[1]));
                    List<Practitioner> practitionerId = patient.getPractitioner();
                    practitionerId.add(practitioner);
                    patient.setPractitioner(practitionerId);
                    patientRepository.save(patient);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
