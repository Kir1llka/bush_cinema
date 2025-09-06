package ru.bush.bush_cinema.service.exceptions;

public class NoSessionException extends RuntimeException {
    public NoSessionException(String message) {
        super(message);
    }
}
