package com.bluezz.moviepack.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@CrossOrigin
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
       http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().formLogin().disable()
                .securityMatcher("/")
                .authorizeHttpRequests(registry -> registry
//                                .requestMatchers(HttpMethod.GET,"/login").permitAll()
//                        .requestMatchers(HttpMethod.POST,"/movie").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE,"/movie/**").hasRole("ADMIN")
//                        .requestMatchers("/movie").hasRole("ADMIN")
//                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/auth/login").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }




}
