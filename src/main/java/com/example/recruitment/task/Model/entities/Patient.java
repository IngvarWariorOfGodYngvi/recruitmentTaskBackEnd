package com.example.recruitment.task.Model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String city;
    private String createdAt;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Practitioner> practitioner = new ArrayList<>();

}
