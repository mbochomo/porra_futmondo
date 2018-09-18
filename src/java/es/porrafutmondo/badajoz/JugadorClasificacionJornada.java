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
public class JugadorClasificacionJornada implements Comparable {

    private String id_jugador;
    private int puntos;
    private int totales;
    private int premio;

    public JugadorClasificacionJornada(String id_jugador, int puntos, int totales) {
        this.id_jugador = id_jugador;
        this.puntos = puntos;
        this.totales = totales;
        this.premio = 0;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getTotales() {
        return totales;
    }

    public void setTotales(int totales) {
        this.totales = totales;
    }

    public int getPremio() {
        return premio;
    }

    public void setPremio(int premio) {
        this.premio = premio;
    }

    @Override
    public String toString() {
        return "JugadorClasificacionJornada{" + "id_jugador=" + id_jugador + ", puntos=" + puntos + ", totales=" + totales + ", premio=" + premio + '}';
    }

    @Override
    public int compareTo(Object o) {
        JugadorClasificacionJornada J = (JugadorClasificacionJornada) o;
        if (puntos < J.getPuntos()) {
            return 1;
        } else if (puntos > J.getPuntos()) {
            return -1;
        } else if (totales < J.getTotales()) {
            return 1;
        } else if (totales > J.getTotales()) {
            return -1;
        }
        return 0;
    }

}
