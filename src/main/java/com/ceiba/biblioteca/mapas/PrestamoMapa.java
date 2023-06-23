package com.ceiba.biblioteca.mapas;

import com.ceiba.biblioteca.dtos.PrestamoRespuestaDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaPOSTDTO;
import com.ceiba.biblioteca.entidades.Prestamo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PrestamoMapa {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "fechaMaximaDevolucion", target = "fechaMaximaDevolucion")
    })
    PrestamoRespuestaPOSTDTO mapearPrestamo(Prestamo prestamo);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "isbn", target = "isbn"),
            @Mapping(source = "identificacionUsuario", target = "identificacionUsuario"),
            @Mapping(source = "tipoUsuario", target = "tipoUsuario"),
            @Mapping(source = "fechaMaximaDevolucion", target = "fechaMaximaDevolucion")

    })
    PrestamoRespuestaDTO mapearPrestamoRespuesta(Prestamo prestamo);


}
