package com.example.yousavebackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "blood_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @OneToMany(mappedBy = "bloodType")
    private List<User> users;
}