package com.example.yousavebackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO {
    private Long userId;
    private String accessToken;
    private String role;
    private String firstname;
    private String lastname;
}

