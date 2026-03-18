package com.smartpower.power_monitor.controller;

import com.smartpower.power_monitor.model.User;
import com.smartpower.power_monitor.repository.UserRepository;
import com.smartpower.power_monitor.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ============================
    // GET ALL USERS
    // ============================
    @GetMapping("/all")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    // ============================
    // ✅ GET LOGGED IN USER PROFILE
    // ============================
    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(
            @RequestHeader("Authorization") String token) {

        // ✅ FIX: no .orElseThrow() — findByUsername returns User directly
        String username = jwtUtil.extractUsername(
                token.replace("Bearer ", ""));

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return ResponseEntity.ok(user);
    }

    // ============================
    // DELETE USER
    // ============================
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return "User Deleted";
    }

    // ============================
    // CHANGE ROLE
    // ============================
    @PutMapping("/role/{id}")
    public User changeRole(@PathVariable Long id,
                           @RequestParam String role){
        User user = userRepository.findById(id).orElseThrow();
        user.setRole(role);
        return userRepository.save(user);
    }
}