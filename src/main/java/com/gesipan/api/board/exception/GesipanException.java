package com.gesipan.api.board.exception;

public abstract class GesipanException extends RuntimeException{

    public GesipanException(String message) {
        super(message);
    }

    public GesipanException(String message, Throwable cause) {
        super(message,cause);
    }

    public abstract int getStatusCode();

}
