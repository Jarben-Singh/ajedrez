package com.torneos.ajedrez.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Partida {

    private long partidaId;
    private String ritmo;
    private long jugadorBlancasId;
    private long jugadorNegrasId;
    private String apertura;
    private int numeroJugadas;
    private String estado;
    private long resultado; // Se colocará el ID del ganador, en caso de ser un empate el resultado será 0.5.
    private String tiempoTotal;

    public void setId(long id) {
        this.partidaId = id;
    }

}
