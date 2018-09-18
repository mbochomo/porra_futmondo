/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

/**
 *
 * @author marco
 */
public class Apuesta {
    int njornada;
    int npartido;
    String id_jugador;
    int goles_local;
    int goles_visitante;
    int puntos;

    public Apuesta(int njornada, int npartido, String id_jugador, int goles_local, int goles_visitante, int puntos) {
        this.njornada = njornada;
        this.npartido = npartido;
        this.id_jugador = id_jugador;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
        this.puntos = puntos;
    }

    public int getNjornada() {
        return njornada;
    }

    public void setNjornada(int njornada) {
        this.njornada = njornada;
    }

    public int getNpartido() {
        return npartido;
    }

    public void setNpartido(int npartido) {
        this.npartido = npartido;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getGoles_local() {
        return goles_local;
    }

    public void setGoles_local(int goles_local) {
        this.goles_local = goles_local;
    }

    public int getGoles_visitante() {
        return goles_visitante;
    }

    public void setGoles_visitante(int goles_visitante) {
        this.goles_visitante = goles_visitante;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Apuesta{" + "njornada=" + njornada + ", npartido=" + npartido + ", id_jugador=" + id_jugador + ", goles_local=" + goles_local + ", goles_visitante=" + goles_visitante + ", puntos=" + puntos + '}';
    }
    
    
}
