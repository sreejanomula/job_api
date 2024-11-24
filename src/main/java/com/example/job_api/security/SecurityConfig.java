package com.example.job_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()  // Disable CSRF if it's a stateless API (e.g., REST API)
                .authorizeRequests()
                .requestMatchers("/api/v1/jobs/**").hasRole("USER")
                .requestMatchers("/api/v1/jobs/{job_id}").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()  // Protect other endpoints
                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Use PasswordEncoder to encode the passwords
        String encodedAdminPassword = passwordEncoder().encode("admin");
        String encodedUserPassword = passwordEncoder().encode("user");

        UserDetails adminUser = User.builder()
                .username("admin")
                .password(encodedAdminPassword)
                .roles("ADMIN")
                .build();

        UserDetails normalUser = User.builder()
                .username("user")
                .password(encodedUserPassword)
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(adminUser, normalUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
