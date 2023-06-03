package com.bluezz.moviepack.web;

import com.bluezz.moviepack.dto.LoginRequest;
import com.bluezz.moviepack.dto.LoginResponse;
import com.bluezz.moviepack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.attemptLogin(request.getUsername(),request.getPassword());


    }
}
