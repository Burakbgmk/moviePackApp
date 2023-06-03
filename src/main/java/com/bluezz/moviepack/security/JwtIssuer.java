package com.bluezz.moviepack.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin
@Component
@RequiredArgsConstructor
public class JwtIssuer {

    private final JwtProperties jwtProperties;

    public String issue(Long userId, String email, List<String> roles) {
        return JWT.create()
                .withSubject(String.valueOf(userId))
                .withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
                .withClaim("e",email)
                .withClaim("a",roles)
                .sign(Algorithm.HMAC256(jwtProperties.getSecretKey()));
    }

}
