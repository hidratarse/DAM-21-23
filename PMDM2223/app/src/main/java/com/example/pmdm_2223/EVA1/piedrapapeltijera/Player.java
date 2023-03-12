package com.example.pmdm_2223.EVA1.piedrapapeltijera;

public class Player {

    int puntuación, jugada;

    public int getJugada() {return jugada;}

    public void setJugada(int jugada) {this.jugada = jugada;}

    public Player(int puntuación) {this.puntuación = puntuación;}

    public void setPuntuación(int puntuación) {
        this.puntuación = puntuación;
    }

    public int getPuntuación() {
        return puntuación;
    }

    public int gana(){
        return puntuación++;
    }

    public int pierde(){
        return puntuación--;
    }
}
