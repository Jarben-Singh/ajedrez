package com.torneos.ajedrez.controller;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.model.Partida;
import com.torneos.ajedrez.service.PartidaServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
        ArrayList<Partida> partidas;
        partidas = partidaServicio.obtenerListaPartidas();
        return ResponseEntity.ok(partidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity getJugador(@PathVariable long id) {
        log.info("Iniciando busqueda de partida por id ", id);
        return new ResponseEntity<>(partidaServicio.buscarPartida(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity guardarJugador(@RequestBody Partida partida){
        log.info("Iniciando creacion de partida ", partida);
        return new ResponseEntity<>(partidaServicio.crearPartida(partida), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity actualizarPartida(
            @PathVariable long id,
            @RequestBody Partida partida){
        log.info("Iniciando actualización de partida: ", partida);
        return new ResponseEntity<>(partidaServicio.actualizarPartida(partida, id), HttpStatus.OK);
    }

    @PutMapping("/{id}/finalizar")
    public ResponseEntity actualizarJugador(
            @PathVariable long id,
            @RequestBody HashMap<String, Long> body // ID del ganador
            ){
        long resultado = body.get("resultado");
        log.info("Finalizando partida");
        return new ResponseEntity<>(partidaServicio.finalizarPartida(id, resultado), HttpStatus.OK);
    }
}
