package com.studentplatform.backend.controller;

import com.studentplatform.backend.model.User;
import com.studentplatform.backend.dto.RegisterRequest;
import com.studentplatform.backend.dto.LoginRequest;
import com.studentplatform.backend.service.UserService;
import com.studentplatform.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:5176"})
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            if (userService.existsByEmail(registerRequest.getEmail())) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "User already exists"
                ));
            }

            User user = new User();
            user.setName(registerRequest.getName());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setRole(User.Role.valueOf(registerRequest.getRole().toLowerCase()));

            User savedUser = userService.createUser(user);
            String token = jwtUtil.generateToken(savedUser.getEmail(), savedUser.getRole().toString());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("data", Map.of(
                "user", savedUser,
                "token", token
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Registration failed: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> userOpt = userService.getUserByEmail(loginRequest.getEmail());
            if (userOpt.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid credentials"
                ));
            }

            User user = userOpt.get();
            if (!userService.validatePassword(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Invalid credentials"
                ));
            }

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("data", Map.of(
                "user", user,
                "token", token
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                "success", false,
                "message", "Login failed: " + e.getMessage()
            ));
        }
    }
}