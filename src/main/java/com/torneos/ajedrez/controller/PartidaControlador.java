package com.torneos.ajedrez.controller;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.model.Partida;
import com.torneos.ajedrez.service.PartidaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/partidas")
public class PartidaControlador {

    private final PartidaServicio partidaServicio;

    public PartidaControlador(PartidaServicio partidaServicio) {
        this.partidaServicio = partidaServicio;
    }

    @GetMapping
    public ResponseEntity obtenerPartidas() {
        log.info("Obteniendo todas los partidas");
        ArrayList<Partida> partidas = new ArrayList<>();
        return ResponseEntity.ok(partidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getJugador(@PathVariable long id) {
        log.info("Iniciando busqueda de partida por id {}", id);
        return new ResponseEntity<>(partidaServicio.buscarPartida(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity guardarJugador(@RequestBody Partida partida){
        log.info("Iniciando creacion de partida {}", partida);
        return new ResponseEntity<>(partidaServicio.crearPartida(partida), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPartida(
            @PathVariable long id,
            @RequestBody Partida partida){
        log.info("Iniciando actualización de partida: {}", partida);
        return new ResponseEntity<>(partidaServicio.actualizarPartida(partida, id), HttpStatus.OK);
    }
}
