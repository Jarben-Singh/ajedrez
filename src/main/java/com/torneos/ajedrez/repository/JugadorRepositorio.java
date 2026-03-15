package com.torneos.ajedrez.repository;

import com.torneos.ajedrez.model.Jugador;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JugadorRepositorio {

    private final ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    long id = 1L;

    public Jugador guardarJugador(Jugador jugador) {
        jugador.setId(id);
        id++;
        jugadores.add(jugador);
        return jugador;
    }

    public Jugador getJugador(long id) {
        for (Jugador jugador : jugadores) {
            if (jugador.getJugadorId() == id) {
                return jugador;
            }
        }
        return null;
    }
}