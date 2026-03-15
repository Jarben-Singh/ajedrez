package com.torneos.ajedrez.model;

public class Jugador {

    private long jugadorId;
    private String nombreCompleto;
    private String genero;
    private double elo;
    private int edad;
    private String nacionalidad;

    public Jugador(int jugadorId, String nombreCompleto, String genero, double elo, int edad, String nacionalidad) {
        this.jugadorId = jugadorId;
        this.nombreCompleto = nombreCompleto;
        this.genero = genero;
        this.elo = elo;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }

    public void setId(long jugadorId) {
        this.jugadorId = jugadorId;
    }
}
