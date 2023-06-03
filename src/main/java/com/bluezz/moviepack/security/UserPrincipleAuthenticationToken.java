package com.bluezz.moviepack.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@CrossOrigin
public class UserPrincipleAuthenticationToken extends AbstractAuthenticationToken {

    private final UserPrinciple principle;
    public UserPrincipleAuthenticationToken(UserPrinciple principle) {
        super(principle.getAuthorities());
        this.principle = principle;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principle;
    }
}
