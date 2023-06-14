package com.bcipriano.codeGenerator.exception.resourceExceptions;

import com.bcipriano.codeGenerator.exception.InvalidCpfException;
import com.bcipriano.codeGenerator.exception.InvalidEmailException;
import com.bcipriano.codeGenerator.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionController {

    @ExceptionHandler(InvalidCpfException.class)
    public ResponseEntity<StandartError> invalidCpfException(InvalidCpfException invalidCpfException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(invalidCpfException.getMessage())
                .error("Invalid cpf exception")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<StandartError> invalidEmailException(InvalidEmailException invalidEmailException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(invalidEmailException.getMessage())
                .error("Invalid e-mail exception")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandartError> NotFoundException(NotFoundException notFoundException, HttpServletRequest servletRequest) {
        StandartError standartError = StandartError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(notFoundException.getMessage())
                .error("Not found exception")
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standartError);
    }

}
