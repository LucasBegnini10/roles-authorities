package com.server.auth.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<Object> badCredentialsException(BadCredentialsException exception){
        return RestResponseHandler.generateResponse("Unauthorized", HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(SQLException.class)
    private ResponseEntity<Object> sqlException(SQLException exception){
        return RestResponseHandler.generateResponse("Error", HttpStatus.BAD_REQUEST, exception.getLocalizedMessage());
    }
}
