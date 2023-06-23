package com.ceiba.biblioteca.validaciones;

import com.ceiba.biblioteca.entidades.Prestamo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrestamoValidaciones {

    public boolean verificarUsuario(Integer tipousuario){
        return !(tipousuario == 1 || tipousuario == 2 || tipousuario == 3);
    }

    public boolean verificarISB(String isbn){
        return (isbn.length() <= 10) ? false : true;
    }

    public boolean verificarPrestamosInvitados(List<Prestamo> lista, Integer tipousuario) {
        return (lista.size() >= 1  && tipousuario == 3) ? true : false;
    }


}
