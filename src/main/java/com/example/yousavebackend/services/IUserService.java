package com.example.yousavebackend.services;

import com.example.yousavebackend.DTOs.RegisterRequestDTO;
import com.example.yousavebackend.DTOs.User.*;
import com.example.yousavebackend.entities.Role;
import com.example.yousavebackend.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {
    UserResponseDTO register(RegisterRequestDTO registerRequestDTO) throws Exception;
    Role getRoleByName(String role_name);
    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> getUserById(Long id);
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO) throws Exception;
    UserResponseDTO updateUser(Long id, UserProfilDTO userRequestDTO) throws Exception;
    void deleteUser(Long id);
    long countAllUsers();
    Optional<User> findByEmail(String email);
    List<UserBasicInfoDTO> getAllUsersBasicInfo();
    void changeUserRole(Long userId, UserRoleDto newRoleName) throws Exception;
    List<UserBasicInforDTO> getAllUsersWithRoles();



}