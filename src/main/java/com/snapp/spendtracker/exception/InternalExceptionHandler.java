package com.snapp.spendtracker.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class InternalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error(ex.getCause().getMessage());
        return ResponseEntity.badRequest()
            .body("Internal Server Error.");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentialException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());

    }

    @ExceptionHandler(InvalidInputDataException.class)
    public ResponseEntity<String> handleUserInputException(InvalidInputDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     *
     * @param ex this exception usually throws when validation of request data is failed.
     *           in project when throw : InvalidInputDataException
     * @return ResponseEntity including cuase message + status code
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(HttpMessageNotReadableException ex) {
        String rootCauseMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rootCauseMessage);
    }



}
