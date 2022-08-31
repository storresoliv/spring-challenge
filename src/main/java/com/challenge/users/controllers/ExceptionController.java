package com.challenge.users.controllers;

import com.challenge.users.dtos.ErrorDto;
import com.challenge.users.factories.DtoFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionController {
    private int MAX_CHARS = 135;

    private ResponseEntity<ErrorDto> handleResponse(HttpStatus status, String message, Exception ex) {
        log.info(String.format("[Error Handler][%s] %s", ex.getClass().toString(), ex.getMessage().toString()));

        if (message.length() >= this.MAX_CHARS) {
            message = String.format("%s...", message.substring(0, MAX_CHARS));
        }

        ErrorDto errorDto = DtoFactory.createErrorDto(message);

        return ResponseEntity.status(status).body(errorDto);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException ex) {
        return handleResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getConstraintViolations().toString(), ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return handleResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMostSpecificCause().toString(), ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleResponse(HttpStatus.BAD_REQUEST, String.format("Param %s is required", ex.getFieldError().getField().toString()), ex);
    }
}
