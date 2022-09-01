package com.gesipan.api.board.exception;

public class BoardNotFound extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public BoardNotFound() {
        super(MESSAGE);
    }

    public BoardNotFound( Throwable cause) {
        super(MESSAGE, cause);
    }
}
