package com.nilo.cadence_test.exceptions;

import com.nilo.cadence_test.model.UserAccess;

public class OpenedSessionException extends Exception{
    public OpenedSessionException(UserAccess userAccess){
        super(String.format(
                "User %d already has an opened session on computer %d started at %s.",
                userAccess.getUser().getId(), userAccess.getComputer().getId(), userAccess.getStartAt().toString()
                ));
    }
}
