package com.torneos.ajedrez.repository;

import com.torneos.ajedrez.model.Jugador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JugadorRepositorio {

    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    public ArrayList<Jugador> ObtenerListaJugadores() {
        return jugadores;
    }

    long id = 1L;

    public Jugador guardarJugador(Jugador jugador) {
        jugador.setId(id);
        id++;
        jugadores.add(jugador);
        return jugador;
    }

    public Jugador buscarJugador(long id) {
        for (Jugador jugador : jugadores) {
            if (jugador.getJugadorId() == id) {
                return jugador;
            }
        }
        return null;
    }

    public Jugador actualizarJugador(long id, Jugador jugadorActualizado) {
        for (Jugador jugador : jugadores) {
            if (jugador.getJugadorId() == id) {
                jugador.setNombreCompleto(jugadorActualizado.getNombreCompleto());
                jugador.setGenero(jugadorActualizado.getGenero());
                jugador.setElo(jugadorActualizado.getElo());
                jugador.setEdad(jugadorActualizado.getEdad());
                jugador.setNacionalidad(jugadorActualizado.getNacionalidad());
                return jugador;
            }
        }
        return null;
    }
}