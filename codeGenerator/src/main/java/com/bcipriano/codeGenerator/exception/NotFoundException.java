package com.bcipriano.codeGenerator.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public NotFoundException() {
        super("Register not found");
    }

    public NotFoundException(String message){
        super(message);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}
