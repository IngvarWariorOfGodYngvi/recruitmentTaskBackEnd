package com.example.recruitment.task.Model.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PatientDTO {

    private String firstName;
    private String lastName;

    private long countVisits;
}
