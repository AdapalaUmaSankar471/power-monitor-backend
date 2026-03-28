package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.User;
import com.smartpower.power_monitor.repository.UserRepository;
import com.smartpower.power_monitor.dto.LoginResponse;
import com.smartpower.power_monitor.dto.LoginRequest;
import com.smartpower.power_monitor.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /////////////////////////////////////////////////////////
    // REGISTER USER
    /////////////////////////////////////////////////////////
    @PostMapping("/register")
    public User register(@RequestBody User user){

        // ✅ CLEAN USERNAME
        String username = user.getUsername().trim().toLowerCase();

        if(userRepository.findByUsername(username) != null){
            throw new RuntimeException("Username already exists");
        }

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(user.getPassword().trim()));
        user.setRole("USER");

        return userRepository.save(user);
    }

    /////////////////////////////////////////////////////////
    // LOGIN (FINAL FIXED)
    /////////////////////////////////////////////////////////
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        // ✅ SAFE CLEAN INPUT
        String username = request.getUsername() != null 
                ? request.getUsername().trim().toLowerCase() 
                : "";

        String password = request.getPassword() != null 
                ? request.getPassword().trim() 
                : "";

        System.out.println("LOGIN USERNAME: " + username);
        System.out.println("LOGIN PASSWORD: " + password);

        // 🔍 FIND USER
        User user = userRepository.findByUsername(username);

        if(user == null){
            return ResponseEntity.status(401).body("User not found");
        }

        // 🔐 PASSWORD CHECK (BCrypt)
        if(!passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.status(401).body("Wrong password");
        }

        // 🔑 GENERATE TOKEN
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        // ✅ RESPONSE
        LoginResponse response = new LoginResponse(
                user.getUsername(),
                user.getRole(),
                token
        );

        return ResponseEntity.ok(response);
    }
}