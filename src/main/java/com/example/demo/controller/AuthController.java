package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.security.JwtUtil;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) {
	//	String encodedPassword = passwordEncoder.encode(request.getPassword());
		request.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }
	
	 @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody UserRequest request) {
	        User user = userService.findByEmail(request.getEmail());
	        if (user == null ||!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body("Invalid email or password");
	        }
		 
	        String token = jwtUtil.generateToken(user);
	        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
	    }
	}
	
