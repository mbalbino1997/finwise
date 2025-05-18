package org.finwise.java.finwise.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
    @Bean
@SuppressWarnings("removal")
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf
            // Disabilita CSRF solo per le chiamate API sotto /api/**
            .ignoringRequestMatchers("/api/**")
        )
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/api/profile").hasRole("USER")
            .requestMatchers(HttpMethod.GET,  "/api/bankaccount/**").permitAll()
            .requestMatchers("/", "/home").hasRole("ADMIN")
            .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated()
        )
        // Abilita Basic Auth con configurazione di default
        .httpBasic()
        .and()
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
            .permitAll()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        )
        .exceptionHandling();

    return http.build();
}
    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
