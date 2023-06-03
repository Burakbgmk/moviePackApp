package com.bluezz.moviepack.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@Component
public class JwtToPrincipleConverter {

    public UserPrinciple convert(DecodedJWT jwt) {
        return UserPrinciple.builder()
                .userId(Long.valueOf(jwt.getSubject()))
                .username(jwt.getClaim("e").asString())
                .authorities(extractAuthoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAuthoritiesFromClaim(DecodedJWT jwt) {
        var claim = jwt.getClaim("a");
        if(claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);
    }


}
