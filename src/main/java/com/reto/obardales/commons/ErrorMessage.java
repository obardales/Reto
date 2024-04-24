/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reto.obardales.commons;

/**
 *
 * @author USER01
 */
public class ErrorMessage {
    private String mensaje;

    // constructor, getters y setters

    public ErrorMessage() {
    }

    public ErrorMessage(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
