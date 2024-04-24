package com.reto.obardales.exception;

public abstract class BussinesException extends RuntimeException {

    protected String facingMessage;

    public BussinesException(String message, String facingMessage) {
        super(message);
        this.facingMessage = facingMessage;
    }

    public String getFacingMessage() {
        return this.facingMessage;
    }

}
