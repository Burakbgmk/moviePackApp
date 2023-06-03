package com.bluezz.moviepack.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {
    private String username;
    private String password;
}
