package com.example.yousavebackend.Controllers;

import com.example.yousavebackend.DTOs.AuthRequestDTO;
import com.example.yousavebackend.DTOs.JwtResponseDTO;
import com.example.yousavebackend.DTOs.RegisterRequestDTO;
import com.example.yousavebackend.entities.User;
import com.example.yousavebackend.security.JwtService;
import com.example.yousavebackend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;
    @Autowired
    @Qualifier("userServiceImpl")
    IUserService userService;

    @PostMapping("/auth/login")
    public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(),
                                authRequestDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getEmail())).build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDTO registerRequestDTO) throws Exception {
        return ResponseEntity.ok(userService.register(registerRequestDTO));
    }
}