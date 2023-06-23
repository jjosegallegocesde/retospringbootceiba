package com.ceiba.biblioteca.repositorios;

import com.ceiba.biblioteca.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo,Integer> {

    List<Prestamo> findByIdentificacionUsuarioAndTipoUsuario(String identificacionUsuario, Integer tipoUsuario);

}
