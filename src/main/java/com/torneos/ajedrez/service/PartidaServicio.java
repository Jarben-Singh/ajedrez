package com.torneos.ajedrez.service;
import com.torneos.ajedrez.exception.DatoIncorrecto;
import com.torneos.ajedrez.exception.DueloInvalido;
import com.torneos.ajedrez.exception.JugadorInexistente;
import com.torneos.ajedrez.exception.PartidaInexistente;
import com.torneos.ajedrez.model.Partida;
import com.torneos.ajedrez.repository.PartidaRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

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
                                        // SERVICIO CRUD //

    public ArrayList<Partida> obtenerListaPartidas() {
        return partidaRepositorio.ObtenerListaPartidas();
    }

    public Partida buscarPartida(long idPartida) {
        if (validarExistenciaPartida(idPartida)) {
            log.warn("No se ha encontrado la partida");
            throw new PartidaInexistente("No se ha encontrado la partida con el id: {}" + idPartida);
        }

        return partidaRepositorio.buscarPartida(idPartida);
    }

    public Partida crearPartida(Partida partida) {
        if (validarRitmoPartida(partida)) {
            log.warn("Ritmo no valido");
            throw new DatoIncorrecto("Ritmo de partida incorrecto");
        }

        if (validarJugadoresPartida(partida)) {
            log.warn("Jugador no valido");
            throw new JugadorInexistente("Jugador no encontrado");
        }

        if (validarDuelo(partida)) {
            log.warn("Error: El mismo jugador está asignado contra sí mismo");
            throw new DueloInvalido("Un jugador no puede enfrentarse a sí mismo");
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

        if (validarDuelo(partida)) {
            log.warn("Error: El mismo jugador está asignado contra sí mismo");
            throw new DueloInvalido("Un jugador no puede enfrentarse a sí mismo");
        }

        log.info("Actualizando partida {}", partida);
        return partidaRepositorio.actualizarPartida(partida, id);
    }

    public Partida finalizarPartida(long id, long resultado) {
        if (validarExistenciaPartida(id)) {
            log.warn("No se ha encontrado la partida");
            throw new PartidaInexistente("No se ha encontrado la partida con el id: {}" + id);
        }

        if (validarEstadoPartida(id)) {
            log.warn("ERROR: La partida no estaba en curso");
            throw new DatoIncorrecto("No se puede finalizar una partida que no está en curso");
        }

        return partidaRepositorio.finalizarPartida(id, resultado);

    }

    // ---------------------------------------------------------------------------------- //
                                        // VALIDACIONES //

    private boolean validarJugadoresPartida(Partida partida) {

        if (jugadorServicio.validarExistenciaJugador(partida.getJugadorBlancasId())
                || jugadorServicio.validarExistenciaJugador(partida.getJugadorNegrasId())) {
            log.warn("No se encontró el jugador");
            return true;
        }

        return false;
    }

    private boolean validarDuelo(Partida partida) {
        return partida.getJugadorNegrasId() == partida.getJugadorBlancasId();
    }

    private boolean validarEstadoPartida(long id) {
        return !Objects.equals(partidaRepositorio.buscarPartida(id).getEstado(), "EN CURSO");
    }

    private boolean validarRitmoPartida(Partida partida) {


        // Validar el String
        if (partida.getRitmo() == null) {
            return true;
        }

        String ritmo = partida.getRitmo();
        ritmo = ritmo.toUpperCase();

        if (!ritmo.equals("BALA") && !ritmo.equals("BLITZ") && !ritmo.equals("RAPID") && !ritmo.equals("CLASSIC")) {
            return true;
        }

        //Validar que el tiempo coincida con el tipo de juego

        Integer duracion = partida.getTiempoTotal();
        switch (ritmo) {
            case "BALA":
                return duracion >= 3;

            case "BLITZ":
                return duracion >= 10;

            case "RAPID":
                return duracion >= 60;

            case "CLASSIC":
                return duracion <= 60;

            default:
                return false;
        }
    }

    private boolean validarExistenciaPartida (Long id) {
        return partidaRepositorio.buscarPartida(id) == null;
    }
}
