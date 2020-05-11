package com.nilo.cadence_test.exceptions;

public class NoSessionException extends Exception {
    public NoSessionException(Integer userId, Integer computerId) {
        super(String.format("User %d has no opened session on computer %d", userId, computerId));
    }
}
