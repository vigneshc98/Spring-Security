package com.vtech.remembermejwt.utils.jwtutils;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    public String createJwtToken(Authentication authentication, int minutes);
    public Authentication parseJwtToken(String jwtToken);
}
