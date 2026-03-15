package com.torneos.ajedrez.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DatoFaltante.class)
    public ResponseEntity<String> handleCaracterFaltante(DatoFaltante datoFaltante){
        return new ResponseEntity<>(datoFaltante.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatoIncorrecto.class)
    public ResponseEntity<String> handleDatoIncorrecto(DatoIncorrecto datoIncorrecto){
        return new ResponseEntity<>(datoIncorrecto.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JugadorInexistente.class)
    public ResponseEntity<String> handleJugadorInexistente(JugadorInexistente jugadorInexistente){
        return new ResponseEntity<>(jugadorInexistente.toString(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PartidaInexistente.class)
    public ResponseEntity<String> handlePartidaInexistente(PartidaInexistente partidaInexistente){
        return new ResponseEntity<>(partidaInexistente.toString(), HttpStatus.NOT_FOUND);
    }
}
