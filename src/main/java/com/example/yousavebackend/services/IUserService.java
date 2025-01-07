package com.example.yousavebackend.services;

import com.example.yousavebackend.DTOs.RegisterRequestDTO;
import com.example.yousavebackend.entities.Role;
import com.example.yousavebackend.entities.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User register (RegisterRequestDTO registerRequestDTO) throws Exception;
    Role getRoleByName (String role_name );
}
