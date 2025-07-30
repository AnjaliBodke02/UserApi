package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.enumaration.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.ModelMap;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	 private UserRepository userRepository;
	 @Autowired
	 private PasswordEncoder passwordEncoder;

	 @Autowired
	 private ModelMapper modelMapper;
	 
	 
	 public User register(UserRequest request) {
	        User user = new User();
	        user.setName(request.getName());
	        user.setEmail(request.getEmail());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole(Role.ROLE_USER);
	        return userRepository.save(user);
	    }
	 
	
	  public User findByEmail(String email) {
	        return userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	    }
	 
	 public List<UserResponse> getAllUsers() {
		 List<User> all = userRepository.findAll();
		List<UserResponse> userResponseList=new ArrayList<>();
		 for(User user: all){
			 UserResponse userResponse = modelMapper.map(user, UserResponse.class);
			 userResponseList.add(userResponse);
		 }
		 return userResponseList;
	 }
	 public UserResponse getUserById(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getRole());
	    }
	  public void deleteUser(Long id) {
	        userRepository.deleteById(id);
	    }
	 
}
