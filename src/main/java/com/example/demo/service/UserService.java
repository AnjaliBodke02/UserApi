package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
@Service
public interface UserService{
	
	 User register(UserRequest request);
	 
	 User findByEmail(String email);
	 
	 List<UserResponse> getAllUsers();
	 
	 UserResponse getUserById(Long id);
	 
	 void deleteUser(Long id);


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//	
//    public User register(UserRequest request) {
//        User user = new User();
//        user.setName(request.getName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(Role.ROLE_USER);
//        return userRepository.save(user);
//    }
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//    }
//
//    public List<UserResponse> getAllUsers() {
//        return userRepository.findAll().stream()
//                .map(user -> new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole()))
//                .collect(Collectors.toList());
//    }
//    public UserResponse getUserById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
//        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
}
	


