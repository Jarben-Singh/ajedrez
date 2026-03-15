package com.torneos.ajedrez.controller;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.service.JugadorServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/jugadores")
public class JugadorControlador {

    private JugadorServicio jugadorServicio;
    public JugadorControlador(JugadorServicio jugadorServicio) {this.jugadorServicio = jugadorServicio;}

    @GetMapping
    public ResponseEntity getJugadores() {
        log.info("Recuperando todos los jugadores");
        ArrayList<Jugador> jugadores = jugadorServicio.getJugadores();
        return ResponseEntity.ok(jugadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity getJugador(@PathVariable long id) {
        log.info("Buscando jugador por id", id);
        return new ResponseEntity<>(jugadorServicio.getJugador(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity guardarJugador(@RequestBody Jugador jugador){
        log.info("Guardando Jugador: ", jugador);
        return new ResponseEntity<>(jugadorServicio.guardarJugador(jugador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarJugador(
            @PathVariable long id,
            @RequestBody Jugador jugador){
        log.info("Actualizando Jugador: ", jugador);
        return new ResponseEntity<>(jugadorServicio.putJugador(jugador, id), HttpStatus.OK);
    }


}
