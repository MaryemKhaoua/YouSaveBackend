package com.example.yousavebackend.DTOs;

import com.example.yousavebackend.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    
   private String firstname;
   private String lastname;
   private String email;
   private String password;
   private String phone;
   public LocalDate DateOfBirth;
   private Collection<String> roles;
}
