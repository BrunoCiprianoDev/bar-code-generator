package com.bcipriano.codeGenerator.exception;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidEmailException() {
        super("Invalid e-mail");
    }

    public InvalidEmailException(String message){
        super(message);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}
