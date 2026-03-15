package com.torneos.ajedrez.service;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.repository.JugadorRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JugadorServicio {

    private JugadorRepositorio jugadorRepositorio;
    public JugadorServicio(JugadorRepositorio jugadorRepositorio) {this.jugadorRepositorio = jugadorRepositorio;}


    public ArrayList<Jugador> getJugadores() {
        return jugadorRepositorio.getJugadores();
    }

    public Jugador guardarJugador(Jugador jugador){
        return jugadorRepositorio.guardarJugador(jugador);
    }


}
