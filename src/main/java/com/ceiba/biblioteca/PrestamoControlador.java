package com.ceiba.biblioteca;


import com.ceiba.biblioteca.dtos.PrestamoDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaDTO;
import com.ceiba.biblioteca.dtos.PrestamoErrorDTO;
import com.ceiba.biblioteca.dtos.PrestamoRespuestaPOSTDTO;
import com.ceiba.biblioteca.entidades.Prestamo;
import com.ceiba.biblioteca.servicios.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;

    @PostMapping
    public ResponseEntity<PrestamoDTO> registrar(@RequestBody Prestamo prestamo){
        try{
            PrestamoRespuestaPOSTDTO prestamoRegistrado=prestamoServicio.registrarPrestamo(prestamo);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoRegistrado);
        }catch(Exception error){
            PrestamoErrorDTO errorMensaje = new PrestamoErrorDTO();
            errorMensaje.setMensaje(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMensaje);

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrestamoDTO>buscarPorId(@PathVariable Integer id){
        try{
            PrestamoRespuestaDTO prestamoEncontrado=prestamoServicio.buscarPorId(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(prestamoEncontrado);
        }catch(Exception error){
            PrestamoErrorDTO errorMensaje = new PrestamoErrorDTO();
            errorMensaje.setMensaje(error.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorMensaje);

        }

    }




}

