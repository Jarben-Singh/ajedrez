package com.torneos.ajedrez.service;

import com.torneos.ajedrez.exception.DatoFaltante;
import com.torneos.ajedrez.exception.DatoIncorrecto;
import com.torneos.ajedrez.exception.JugadorInexistente;
import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.repository.JugadorRepositorio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class JugadorServicio {

    // ---------------------------------------------------------------------------------- //
                                // Inyección de dependencias //
    private final JugadorRepositorio jugadorRepositorio;
    public JugadorServicio(JugadorRepositorio jugadorRepositorio) {this.jugadorRepositorio = jugadorRepositorio;}


    // ---------------------------------------------------------------------------------- //
                                        // SERVICIO //

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


}
