package com.example.MonitorSensors.config;

import com.example.MonitorSensors.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.GET, "/sensors")
                                                                            .hasAnyRole("VIEWER", "ADMINISTRATOR")
                                                                            .requestMatchers("/sensors/**")
                                                                            .hasRole("ADMINISTRATOR")
                                                                            .requestMatchers("/users/**").permitAll()
                                                                            .anyRequest().authenticated())
            .httpBasic(customizer -> {});
        
        return http.build();
    }
    
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                                         .map(user -> org.springframework.security.core.userdetails.User.builder()
                                                                                                        .username(user.getUsername())
                                                                                                        .password(user.getPassword())
                                                                                                        .roles(user.getRole().getName())
                                                                                                        .build())
                                         .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}