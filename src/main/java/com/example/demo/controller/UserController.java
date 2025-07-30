package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserResponse;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	 private UserService userService;
	 
//	@Autowired
//	private  UserRepository repo;
	
	 @GetMapping("/getAllUser")
	    public ResponseEntity<List<UserResponse>> getAllUsers() {
	        return ResponseEntity.ok(userService.getAllUsers());
	    }
	 	
	 @GetMapping("/{id}")
	    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
	        return ResponseEntity.ok(userService.getUserById(id));
	    }
	 
	 @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return ResponseEntity.ok("User deleted successfully");
	    }
}
