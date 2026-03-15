package com.torneos.ajedrez.repository;

import com.torneos.ajedrez.model.Jugador;
import com.torneos.ajedrez.model.Partida;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PartidaRepositorio {

    private ArrayList<Partida> partidas = new ArrayList<Partida>();

    public ArrayList<Partida> ObtenerListaPartidas() {
        return partidas;
    }

    long id = 1L;

    public Partida crearPartida(Partida partida) {
        partida.setId(id);
        id++;
        partidas.add(partida);
        return partida;
    }

    public Partida buscarPartida(long id) {
        for (Partida partida : partidas) {
            if (partida.getPartidaId() == id) {
                return partida;
            }
        }
        return null;
    }

    public Partida actualizarPartida(Partida partida, Long id) {
        for (Partida p : partidas) {
            if (p.getPartidaId() == id) {
                p.setApertura(partida.getApertura());
                p.setEstado(partida.getEstado());
                p.setJugadorBlancasId(partida.getJugadorBlancasId());
                p.setJugadorNegrasId(partida.getJugadorNegrasId());
                p.setRitmo(partida.getRitmo());
                p.setApertura(partida.getApertura());
                p.setEstado(partida.getEstado());
                p.setResultado(partida.getResultado());
                return p;
            }
        }
        return null;
    }
}
