package com.example.yousavebackend.DTOs.User;

import lombok.Data;
import java.util.Set;

@Data
public class UserBasicInforDTO {
    private Long id; // Add the id field
    private String firstname;
    private String lastname;
    private Set<String> roles;

    public UserBasicInforDTO() {}

    public UserBasicInforDTO(Long id, String firstname, String lastname, Set<String> roles) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.roles = roles;
    }
}
