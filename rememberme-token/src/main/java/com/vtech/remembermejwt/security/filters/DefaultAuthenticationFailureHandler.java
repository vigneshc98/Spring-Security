package com.vtech.remembermejwt.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vtech.remembermejwt.exceptions.RestErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DefaultAuthenticationFailureHandler.class);

    private final ObjectMapper objectMapper;

    public DefaultAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LOGGER.warn(exception.getMessage());

        HttpStatus httpStatus = translateAuthenticationException(exception);

        response.setStatus(httpStatus.value());
        response.setContentType(APPLICATION_JSON_VALUE);

        writeResponse(response.getWriter(), httpStatus, exception);
    }

    protected HttpStatus translateAuthenticationException(AuthenticationException exception) {
        return UNAUTHORIZED;
    }

    protected void writeResponse(
            Writer writer, HttpStatus httpStatus, AuthenticationException exception) throws IOException {

        RestErrorResponse restErrorResponse = RestErrorResponse.of(httpStatus, exception);
        objectMapper.writeValue(writer, restErrorResponse);
    }
}
