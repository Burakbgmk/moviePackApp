package com.bluezz.moviepack.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt")
public class JwtProperties {
    /**
     * Secret key used for issuing JWT
     */
    private String secretKey;
}
