package com.torneos.ajedrez.controller;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.service.JugadorServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController
@RequestMapping("/jugadores")
public class JugadorControlador {

    private JugadorServicio jugadorServicio;
    public JugadorControlador(JugadorServicio jugadorServicio) {this.jugadorServicio = jugadorServicio;}

    @GetMapping
    public ResponseEntity getJugadores() {
        ArrayList<Jugador> jugadores = jugadorServicio.getJugadores();
        return ResponseEntity.ok(jugadores);
    }

    @PostMapping
    public ResponseEntity guardarJugador(@RequestBody Jugador jugador){
        return new ResponseEntity<>(jugadorServicio.guardarJugador(jugador), HttpStatus.CREATED);
    }


}
