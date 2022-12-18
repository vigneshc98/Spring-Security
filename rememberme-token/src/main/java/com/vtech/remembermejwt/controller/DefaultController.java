package com.vtech.remembermejwt.controller;

import com.vtech.remembermejwt.exceptions.RestErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class DefaultController {

    @RequestMapping
    public ResponseEntity<RestErrorResponse> handleUnmappedRequest(final HttpServletRequest request) {
        return ResponseEntity.status(NOT_FOUND).body(RestErrorResponse.of(NOT_FOUND));
    }

}
