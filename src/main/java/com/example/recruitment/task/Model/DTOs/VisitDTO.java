package com.example.recruitment.task.Model.DTOs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class VisitDTO {

    private String visit;
    private Long numberOfVists;
    private Long patientsNumberOfVists;
}
