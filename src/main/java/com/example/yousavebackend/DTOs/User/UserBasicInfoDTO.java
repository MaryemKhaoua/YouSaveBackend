package com.example.yousavebackend.DTOs.User;

import lombok.Data;

@Data
public class UserBasicInfoDTO {
    private String name;
    private String bloodType;
    private String city;
    private String phone;

    public UserBasicInfoDTO(String name, String bloodType, String city, String phone) {
        this.name = name;
        this.bloodType = bloodType;
        this.city = city;
        this.phone = phone;
    }
}