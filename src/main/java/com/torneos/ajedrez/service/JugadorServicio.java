package com.torneos.ajedrez.service;

import com.torneos.ajedrez.exception.DatoFaltante;
import com.torneos.ajedrez.exception.DatoIncorrecto;
import com.torneos.ajedrez.exception.JugadorInexistente;
import com.torneos.ajedrez.exception.PartidaActiva;
import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.model.Partida;
import com.torneos.ajedrez.repository.JugadorRepositorio;
import com.torneos.ajedrez.repository.PartidaRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class JugadorServicio {

    // ---------------------------------------------------------------------------------- //
                                // Inyección de dependencias //
    private final JugadorRepositorio jugadorRepositorio;
    private final PartidaRepositorio partidaRepositorio;
    public JugadorServicio(JugadorRepositorio jugadorRepositorio, PartidaRepositorio partidaRepositorio) {
        this.jugadorRepositorio = jugadorRepositorio;
        this.partidaRepositorio = partidaRepositorio;
    }


    // ---------------------------------------------------------------------------------- //
                                        // SERVICIO CRUD //

    public ArrayList<Jugador> obtenerListaJugadores() {
        log.info("Buscando jugadores");
        return jugadorRepositorio.ObtenerListaJugadores();
    }

    public Jugador guardarJugador(Jugador jugador){
        if (validarCamposVacios(jugador)){
            log.warn("Faltan datos del jugador");
            throw new DatoFaltante("No deben haber campos vacios");
        }

        if (validarElo(jugador)){
            log.warn("El dato de ELO es incorrecto");
            throw new DatoIncorrecto("El ELO debe ser mayor o igual a 0");
        }

        log.info("Guardando Jugador: {}", jugador);
        return jugadorRepositorio.guardarJugador(jugador);
    }

    public Jugador buscarJugador(long id){
        if (validarExistenciaJugador(id)) {
            log.info("No se encontro el jugador");
            throw new JugadorInexistente("El jugador no existe");
        }

        log.info("Buscando jugador por ID: {}", id);
        return jugadorRepositorio.buscarJugador(id);
    }

    public Jugador actualizarJugador(Jugador jugador, long id){
        if (validarExistenciaJugador(id)) {
            log.info("No se encontro el jugador");
            throw new JugadorInexistente("El jugador no existe");
        }

        if (validarCamposVacios(jugador)){
            log.warn("Faltan datos del jugador");
            throw new DatoFaltante("No deben haber campos vacios");
        }

        if (validarElo(jugador)){
            log.warn("El dato de ELO es incorrecto");
            throw new DatoIncorrecto("El ELO debe ser mayor o igual a 0");
        }

        log.info("Buscando jugadores");
        return jugadorRepositorio.actualizarJugador(id, jugador);
    }

    public void eliminarJugador(long id){
        if (validarExistenciaJugador(id)) {
            log.warn("No se encontro el jugador");
            throw new JugadorInexistente("El jugador no existe");
        }

        if (validarPartidaActiva(id)) {
        log.warn("El jugador esta en una partida activa");
        throw new PartidaActiva("No se puede eliminar a un jugador que esté en una partida activa");
        }
        jugadorRepositorio.eliminarJugador(id);
    }



    // ---------------------------------------------------------------------------------- //
                                       // VALIDACIONES //

    public boolean validarCamposVacios(Jugador jugador){
        return jugador.getNombreCompleto() == null || jugador.getNombreCompleto().isBlank()
                || jugador.getGenero() == null || jugador.getGenero().isBlank()
                || jugador.getEdad() == null
                || jugador.getNacionalidad() == null || jugador.getNacionalidad().isBlank()
                || jugador.getElo() == null;
    }

    public boolean validarElo(Jugador jugador){
        return jugador.getElo() < 0;
    }

    public boolean validarExistenciaJugador(Long jugadorId){
        return jugadorRepositorio.buscarJugador(jugadorId) == null;
    }

    public boolean validarPartidaActiva (Long id) {
        Partida partida = partidaRepositorio.buscarPartida(id);
        return !partida.getEstado().equals("EN CURSO");
    }
}
