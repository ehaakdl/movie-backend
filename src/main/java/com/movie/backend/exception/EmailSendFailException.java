package com.movie.backend.exception;

public class EmailSendFailException extends RuntimeException{

    public EmailSendFailException(Throwable e) {
        super(e);
    }
    
}
