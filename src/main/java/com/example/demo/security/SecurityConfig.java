package com.example.demo.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.UserService;

@Data
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
	private JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
         	.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                		.requestMatchers("/auth/**").permitAll()
                		.requestMatchers(HttpMethod.GET, "/users/**").hasRole("ADMIN")
                     //   .requestMatchers("/login", "/register").permitAll()
                     //   .requestMatchers(HttpMethod.GET, "/users").permitAll()
                     //   .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                     //   .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("USER", "ADMIN")
                     //   .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
                return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
