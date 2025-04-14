package org.study.studyblogapi.exception;

public class MediaNotValidException extends RuntimeException {
    public MediaNotValidException(String message) {
        super(message);
    }
}
