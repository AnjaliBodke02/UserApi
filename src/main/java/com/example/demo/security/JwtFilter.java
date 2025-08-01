package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final UserService userService;

	public JwtFilter(JwtUtil jwtUtil, @Lazy UserService userService) {
		this.jwtUtil = jwtUtil;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			try {
			String username = jwtUtil.extractUsername(token);
			
			 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	             User user = userService.findByEmail(username);
	             
			if (user != null && jwtUtil.isTokenValid(token, username)) {
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(
						user,
						null,
						List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())) // Use .name() or your getter
				);
				SecurityContextHolder.getContext().setAuthentication(authToken);
				} else {
					 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				        response.getWriter().write("Invalid email or token");
				        return;
				}
			}
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token validation failed: " + e.getMessage());
            return;
		}
		}
			 
		filterChain.doFilter(request, response);
	}
}

