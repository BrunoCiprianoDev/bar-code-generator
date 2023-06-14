package com.bcipriano.codeGenerator.exception;

import org.springframework.http.HttpStatus;

public class InvalidCpfException extends RuntimeException{

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public InvalidCpfException() {
        super("Invalid CPF");
    }

    public InvalidCpfException(String message){
        super(message);
    }

    public HttpStatus getStatus() {
        return this.status;
    }

}
