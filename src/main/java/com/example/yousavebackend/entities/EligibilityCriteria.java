package com.example.yousavebackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "eligibility_criteria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int minAge;
    private int maxAge;
    private int minWeight;

    @ElementCollection
    private List<String> medicalExclusions;
}