package com.ceiba.biblioteca.servicios;

import com.ceiba.biblioteca.dtos.PrestamoRespuestaDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaPOSTDTO;
import com.ceiba.biblioteca.entidades.Prestamo;
import com.ceiba.biblioteca.mapas.PrestamoMapa;
import com.ceiba.biblioteca.repositorios.PrestamoRepositorio;
import com.ceiba.biblioteca.validaciones.PrestamoValidaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio prestamoRepositorio;

    @Autowired
    PrestamoMapa prestamoMapa;

    @Autowired
    PrestamoValidaciones prestamoValidaciones;

    public PrestamoRespuestaPOSTDTO registrarPrestamo(Prestamo datosARegistrar) throws Exception{

        try{

            List<Prestamo> prestamosPrevios=prestamoRepositorio.findByIdentificacionUsuarioAndTipoUsuario(datosARegistrar.getIdentificacionUsuario(),datosARegistrar.getTipoUsuario());

            if(prestamoValidaciones.verificarUsuario(datosARegistrar.getTipoUsuario())){
                throw new Exception("Tipo de usuario no permitido en la biblioteca");
            }else if(prestamoValidaciones.verificarISB(datosARegistrar.getIsbn())){
                throw new Exception("Revisar por favor el ISBN");
            }else if(prestamoValidaciones.verificarPrestamosInvitados(prestamosPrevios,datosARegistrar.getTipoUsuario())){
                throw new Exception("El usuario con identificación "+
                        datosARegistrar.getIdentificacionUsuario()+
                        " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            }else{
                String fechaMaximaCalculada=formatearFecha(obtnerFechaMaxima(datosARegistrar.getTipoUsuario()));
                datosARegistrar.setFechaMaximaDevolucion(fechaMaximaCalculada);
                //System.out.println("La fecha maximas es: "+fechaMaximaCalculada);
                PrestamoRespuestaPOSTDTO prestamoGuardado= prestamoMapa.mapearPrestamo(prestamoRepositorio.save(datosARegistrar));
                return prestamoGuardado;
            }

        }catch(Exception error){
            throw new Exception(error.getMessage());
        }

    }

    public PrestamoRespuestaDTO buscarPorId(Integer id)throws Exception{

        try{
            Optional<Prestamo> prestamoOpcional = prestamoRepositorio.findById(id);
            if(prestamoOpcional.isPresent()){
                System.out.println(prestamoOpcional.get().getFechaMaximaDevolucion());
                PrestamoRespuestaDTO respuesta=prestamoMapa.mapearPrestamoRespuesta(prestamoOpcional.get());
                return respuesta;
            }else{
                throw new Exception("Usuario no encontrado");
            }
        }catch(Exception error){
            throw new Exception(error.getMessage());
        }

    }



    public LocalDate obtnerFechaMaxima(Integer tipoUsuario) throws Exception{
        LocalDate fechaActual= LocalDate.now();
        int diasSumados = 0;
        int diasASumar;

        switch (tipoUsuario) {
            case 1:
                diasASumar = 10;
                break;
            case 2:
                diasASumar = 8;
                break;
            case 3:
                diasASumar = 7;
                break;
            default:
                throw new Exception("Tipo de usuario no permitido en la biblioteca");
        }
        while (diasSumados < diasASumar) {
            fechaActual = fechaActual.plusDays(1);
            if (fechaActual.getDayOfWeek() != DayOfWeek.SATURDAY && fechaActual.getDayOfWeek() != DayOfWeek.SUNDAY) {
                diasSumados++;
            }
        }
        return fechaActual;
    }

    public String formatearFecha(LocalDate fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fecha.format(formato);
        return fechaFormateada;
    }






}
