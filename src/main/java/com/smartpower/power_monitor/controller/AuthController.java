package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.User;
import com.smartpower.power_monitor.repository.UserRepository;
import com.smartpower.power_monitor.dto.LoginResponse;
import com.smartpower.power_monitor.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
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



// REGISTER USER
@PostMapping("/register")
public User register(@RequestBody User user){

    if(userRepository.findByUsername(user.getUsername())!=null){
        throw new RuntimeException("Username already exists");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("USER");

    return userRepository.save(user);
}



// CREATE ADMIN
@PostMapping("/create-admin")
public User createAdmin(@RequestBody User user){

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ADMIN");

    return userRepository.save(user);
}



// CREATE ENERGY MANAGER
@PostMapping("/create-manager")
public User createManager(@RequestBody User user){

    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRole("ENERGY_MANAGER");

    return userRepository.save(user);
}



// LOGIN
@PostMapping("/login")
public LoginResponse login(@RequestBody User login){

    User user=userRepository.findByUsername(login.getUsername());

    if(user==null)
        throw new RuntimeException("User not found");

    if(!passwordEncoder.matches(login.getPassword(),user.getPassword()))
        throw new RuntimeException("Wrong password");

    String token=jwtUtil.generateToken(user.getUsername(),user.getRole());

    return new LoginResponse(
            user.getUsername(),
            user.getRole(),
            token
    );
}

}