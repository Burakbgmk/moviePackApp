package com.bluezz.moviepack.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Component
@RequiredArgsConstructor
public class JwtDecoder {

    private final JwtProperties properties;

    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }

}
