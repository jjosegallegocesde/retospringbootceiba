package com.ceiba.biblioteca.dtos;

public class PrestamoErrorDTO extends PrestamoDTO{

    private String mensaje;

    public PrestamoErrorDTO() {
    }


    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
