package com.movie.backend.exception;

public class EmailSendingFailException extends RuntimeException{

    public EmailSendingFailException(Throwable e) {
        super(e);
    }
    
}
