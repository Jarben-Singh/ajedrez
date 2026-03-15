package com.torneos.ajedrez.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Jugador {

    private long jugadorId;
    private String nombreCompleto;
    private String genero;
    private double elo;
    private int edad;
    private String nacionalidad;

    public void setId(long id) {
        this.jugadorId = id;
    }
}
