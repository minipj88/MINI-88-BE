package com.ujutechnology.api8.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.message.AuthException;

/**
 * @author kei
 * @since 2022-09-01
 */
@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(AuthException.class)
    public ResponseEntity handlerAuthException(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
