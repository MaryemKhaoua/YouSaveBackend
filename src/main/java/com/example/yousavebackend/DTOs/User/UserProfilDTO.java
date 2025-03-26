package com.example.yousavebackend.DTOs.User;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserProfilDTO {
    private String firstname;
    private String lastname;
    private String gender;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Long cityId;
    private Long bloodTypeId;
    }