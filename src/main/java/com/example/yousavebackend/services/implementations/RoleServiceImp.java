package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.repositories.RoleRepository;
import com.example.yousavebackend.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.yousavebackend.entities.Role;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImp implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<String> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
    }

    @Override
    public String getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .map(Role::getName)
                .orElse(null);
    }
}
