package com.torneos.ajedrez.service;
import com.torneos.ajedrez.exception.DatoIncorrecto;
import com.torneos.ajedrez.exception.JugadorInexistente;
import com.torneos.ajedrez.model.Partida;
import com.torneos.ajedrez.repository.PartidaRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class PartidaServicio {

    // ---------------------------------------------------------------------------------- //
                                // Inyección de dependencias //
    private final PartidaRepositorio partidaRepositorio;
    private final JugadorServicio jugadorServicio;
    public PartidaServicio(PartidaRepositorio partidaRepositorio, JugadorServicio jugadorServicio) {
        this.partidaRepositorio = partidaRepositorio;
        this.jugadorServicio = jugadorServicio;
    }

    // ---------------------------------------------------------------------------------- //
                                        // SERVICIO //

    public ArrayList<Partida> obtenerListaPartidas() {
        return partidaRepositorio.ObtenerListaPartidas();
    }

    public Partida buscarPartida(long idPartida) {
        return partidaRepositorio.buscarPartida(idPartida);
    }

    public Partida crearPartida(Partida partida) {
        if (validarRitmoPartida(partida)) {
            log.warn("Ritmo no valido");
            throw new DatoIncorrecto("Ritmo partida incorrecto");
        }

        if (validarJugadoresPartida(partida)) {
            log.warn("Jugador no valido");
            throw new JugadorInexistente("Jugador no encontrado");
        }

        log.info("Creando partida {}", partida);
        return partidaRepositorio.crearPartida(partida);
    }

    public Partida actualizarPartida(Partida partida, long id) {
        if (validarRitmoPartida(partida)) {
            log.warn("Ritmo no valido");
            throw new DatoIncorrecto("Ritmo partida incorrecto");
        }

        if (validarJugadoresPartida(partida)) {
            log.warn("Jugador no valido");
            throw new JugadorInexistente("Jugador no encontrado");
        }

        log.info("Actualizando partida {}", partida);
        return partidaRepositorio.actualizarPartida(partida, id);
    }

    // ---------------------------------------------------------------------------------- //
                                        // VALIDACIONES //

    private boolean validarJugadoresPartida(Partida partida) {

        if (partida.getJugadorBlancasId() == partida.getJugadorNegrasId()) {
            return true;
        }

        if (jugadorServicio.validarExistenciaJugador(partida.getJugadorBlancasId())
                || jugadorServicio.validarExistenciaJugador(partida.getJugadorNegrasId())) {
            log.warn("No se encontró el jugador");
            return true;
        }

        return false;
    }

    private boolean validarRitmoPartida(Partida partida) {

        if (partida.getRitmo() == null) {
            return true;
        }

        String ritmo = partida.getRitmo();
        ritmo = ritmo.toUpperCase();

        if (!ritmo.equals("BALA") && !ritmo.equals("BLITZ") && !ritmo.equals("RAPID") && !ritmo.equals("CLASSIC")) {
            return true;
        }

        return false;
    }
}
