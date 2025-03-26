package com.example.yousavebackend.Controllers;

import com.example.yousavebackend.DTOs.User.*;
import com.example.yousavebackend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private IUserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long userCount = userService.countAllUsers();
        return ResponseEntity.ok(userCount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            UserResponseDTO newUser = userService.saveUser(userRequestDTO);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody UserProfilDTO userRequestDTO) {
        System.out.println("Received PUT request to update user with ID: " + id);
        try {
            UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            e.printStackTrace();  // Log the error
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/basic-info")
    public List<UserBasicInfoDTO> getAllUsersBasicInfo() {
        return userService.getAllUsersBasicInfo();
    }

    @GetMapping("/roles-info")
    public List<UserBasicInforDTO> getAllUsersWithRoles() {
        return userService.getAllUsersWithRoles();
    }

    @PutMapping("/{userId}/role")
    public ResponseEntity<String> changeUserRole(@PathVariable Long userId, @RequestBody UserRoleDto newRoleName) {
        try {
            userService.changeUserRole(userId, newRoleName);

            return ResponseEntity.ok("User role updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user role: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
