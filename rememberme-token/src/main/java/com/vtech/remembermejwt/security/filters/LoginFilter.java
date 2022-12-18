package com.vtech.remembermejwt.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtech.remembermejwt.dto.LoginRequest;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final String LOGIN_REQUEST_ATTRIBUTE = "login_request";

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest =
                    objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            System.out.println("LoginFilter-> username"+loginRequest.getUsername()+", "+loginRequest.getPassword());

            request.setAttribute(LOGIN_REQUEST_ATTRIBUTE, loginRequest);

            return super.attemptAuthentication(request, response);
        } catch (IOException ioe) {
            throw new InternalAuthenticationServiceException(ioe.getMessage(), ioe);
        } finally {
            request.removeAttribute(LOGIN_REQUEST_ATTRIBUTE);
        }
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return super.obtainUsername(request);
    }

    private LoginRequest toLoginRequest(HttpServletRequest request) {
        return (LoginRequest)request.getAttribute(LOGIN_REQUEST_ATTRIBUTE);
    }
}
