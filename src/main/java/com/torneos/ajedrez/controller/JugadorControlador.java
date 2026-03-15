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

    private final JugadorServicio jugadorServicio;
    public JugadorControlador(JugadorServicio jugadorServicio) {this.jugadorServicio = jugadorServicio;}

    @GetMapping
    public ResponseEntity getJugadores() {
        log.info("Recuperando todos los jugadores");
        ArrayList<Jugador> jugadores = jugadorServicio.obtenerListaJugadores();
        return ResponseEntity.ok(jugadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity getJugador(@PathVariable long id) {
        log.info("Iniciando busqueda por id {}", id);
        return new ResponseEntity<>(jugadorServicio.buscarJugador(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity guardarJugador(@RequestBody Jugador jugador){
        log.info("Iniciando guardado de jugador {}", jugador);
        return new ResponseEntity<>(jugadorServicio.guardarJugador(jugador), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarJugador(
            @PathVariable long id,
            @RequestBody Jugador jugador){
        log.info("Iniciando actualización de jugador: {}", jugador);
        return new ResponseEntity<>(jugadorServicio.actualizarJugador(jugador, id), HttpStatus.OK);
    }


}
