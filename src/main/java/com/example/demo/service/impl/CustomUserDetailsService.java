package com.example.demo.service.impl;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetails {
	
	 private User user = new User();

	    public void CustomUserDetails(User user) {
	    	this.user = user;
	    }
	    	
	    	@Override
	        public Collection<? extends GrantedAuthority> getAuthorities() {
	            return List.of(new SimpleGrantedAuthority(user.getRole().name()));
	        }
	    	
	    	 @Override
	    	    public String getPassword() {
	    	        return user.getPassword();
	    	    }
	    	 
	    	 @Override
	    	    public String getUsername() {
	    	        return user.getEmail();
	    	    }
	    	 @Override public boolean isAccountNonExpired() { 
	    		 return true;
	    		 }
	    	 @Override public boolean isAccountNonLocked() { 
	    		 return true; 
	    		 }
	    	 @Override public boolean isCredentialsNonExpired() {
	    		 return true; 
	    		 }
	    	 @Override public boolean isEnabled() { 
	    		 return true;
	    		 }
	    	 
	}
 


