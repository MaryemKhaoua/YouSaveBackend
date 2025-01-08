package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.DTOs.RegisterRequestDTO;
import com.example.yousavebackend.entities.Role;
import com.example.yousavebackend.entities.User;
import com.example.yousavebackend.repositories.RoleRepository;
import com.example.yousavebackend.repositories.UserRepository;
import com.example.yousavebackend.services.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User register(RegisterRequestDTO registerRequestDTO) throws Exception {
        User user = new User();

        if (registerRequestDTO.getFirstname() == null || registerRequestDTO.getFirstname().isEmpty()) {
            throw new Exception("Firstname null or empty");
        }
        if (registerRequestDTO.getLastname() == null || registerRequestDTO.getLastname().isEmpty()) {
            throw new Exception("Lastname null or empty");
        }
        if (registerRequestDTO.getEmail() == null || registerRequestDTO.getEmail().isEmpty()) {
            throw new Exception("Email null or empty");
        }
        if (registerRequestDTO.getPassword() == null || registerRequestDTO.getPassword().isEmpty()) {
            throw new Exception("Password null or empty");
        }

        user.setFirstname(registerRequestDTO.getFirstname());
        user.setLastname(registerRequestDTO.getLastname());
        user.setEmail(registerRequestDTO.getEmail());
        user.setDateOfBirth(registerRequestDTO.getDateOfBirth());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setPhone(registerRequestDTO.getPhone());

        Collection<Role> roles = new ArrayList<>();
        if (registerRequestDTO.getRoles() != null && !registerRequestDTO.getRoles().isEmpty()) {
            for (String roleName : registerRequestDTO.getRoles()) {
                Role role = getRoleByName(roleName);
                if (role == null) {
                    throw new Exception("Role " + roleName + " not found");
                }
                roles.add(role);
            }
        } else {
            roles.add(getRoleByName("USER"));
        }
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @Override
    public Role getRoleByName(String role_name) {
        return this.roleRepository.getRoleByName(role_name);
    }
}