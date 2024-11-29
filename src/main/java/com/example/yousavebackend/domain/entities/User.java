package com.example.yousavebackend.domain.entities;

import com.example.yousavebackend.domain.enums.BloodType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    @OneToMany(mappedBy = "donor")
    private List<Donation> donations;



}
