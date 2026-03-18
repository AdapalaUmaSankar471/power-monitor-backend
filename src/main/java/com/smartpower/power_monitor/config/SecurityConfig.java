package com.smartpower.power_monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                // ✅ Public APIs
                .requestMatchers("/auth/**").permitAll()
                .requestMatchers("/devices/**").permitAll()   // 🔥 IMPORTANT FIX
                .requestMatchers("/power-monitor/**").permitAll() // for WebSocket

                // ✅ Protected APIs
                .requestMatchers("/logs/**").hasAnyRole("ADMIN", "ENERGY_MANAGER")

                // ✅ Allow everything else (for now)
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}