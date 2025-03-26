package com.example.yousavebackend.services;

import java.util.List;

public interface IRoleService {
    List<String> getAllRoles();
    String getRoleByName(String roleName);
}

