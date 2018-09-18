/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

/**
 * Clase auxiliar para obtener el número de la jornada actual y si está o no
 * finalizada.
 * @author marco
 */
public class Jornada {
    private int njornada;
    private Boolean finalizada;

    public Jornada(int njornada, Boolean finalizada) {
        this.njornada = njornada;
        this.finalizada = finalizada;
    }

    public int getNjornada() {
        return njornada;
    }

    public void setNjornada(int njornada) {
        this.njornada = njornada;
    }

    public Boolean getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    @Override
    public String toString() {
        return "Jornada{" + "njornada=" + njornada + ", finalizada=" + finalizada + '}';
    }
    
    
}
