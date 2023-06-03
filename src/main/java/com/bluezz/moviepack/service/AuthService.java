package com.bluezz.moviepack.service;

import com.bluezz.moviepack.dto.LoginResponse;
import com.bluezz.moviepack.security.JwtIssuer;
import com.bluezz.moviepack.security.UserPrinciple;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtIssuer jwtIssuer;

    private final AuthenticationManager authenticationManager;

    public LoginResponse attemptLogin(String username, String password) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        var principle = (UserPrinciple) authentication.getPrincipal();

        var roles = principle.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var token = jwtIssuer.issue(principle.getUserId(), principle.getUsername(), roles);
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}
