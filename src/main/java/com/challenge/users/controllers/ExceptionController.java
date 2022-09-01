package com.challenge.users.controllers;

import com.challenge.users.dtos.ErrorDTO;
import com.challenge.users.exceptions.EmailDuplicatedException;
import com.challenge.users.exceptions.InvalidParameterException;
import com.challenge.users.mappers.ErrorDTOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionController {
    private int MAX_CHARS = 135;

    @Autowired
    private ErrorDTOMapper errorDTOMapper;

    private ResponseEntity<ErrorDTO> handleResponse(HttpStatus status, String message, Exception ex) {
        log.info(String.format("[Error Handler][%s] %s", ex.getClass().toString(), ex.getMessage()));

        if (message.length() >= this.MAX_CHARS) {
            message = String.format("%s...", message.substring(0, MAX_CHARS));
        }

        ErrorDTO errorDto = this.errorDTOMapper.createErrorDto(message);

        return ResponseEntity.status(status).body(errorDto);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ErrorDTO> handleInvalidParameterException(InvalidParameterException ex) {
        return handleResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }

    @ExceptionHandler(EmailDuplicatedException.class)
    public ResponseEntity<ErrorDTO> handleEmailDuplicatedException(EmailDuplicatedException ex) {
        return handleResponse(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage(), ex);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleResponse(HttpStatus.BAD_REQUEST, String.format("Param %s is required", ex.getFieldError().getField()), ex);
    }
}
