package com.reto.obardales.service.exception;

import com.reto.obardales.exception.BussinesException;

public class UserDuplicateException extends BussinesException {

    private static final String DEFAULT = "A duplicate user was found.";

    private String email;

    public UserDuplicateException(String email, String facingMessage) {
        super(DEFAULT, facingMessage);
        this.email = email;
    }

    @Override
    public String getFacingMessage() {
        return String.format(this.facingMessage, this.email);
    }

}
