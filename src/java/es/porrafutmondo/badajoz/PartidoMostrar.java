/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.util.Date;

/**
 *
 * @author marco
 */
public class PartidoMostrar {
    private int njornada;
    private int npartido;
    private String local;
    private String siglaslocal;
    private String visitante;
    private String siglasvisitante;
    private Date fecha;

    public PartidoMostrar(int njornada, int npartido, String local, String siglaslocal, String visitante, String siglasvisitante, Date fecha) {
        this.njornada = njornada;
        this.npartido = npartido;
        this.local = local;
        this.siglaslocal = siglaslocal;
        this.visitante = visitante;
        this.siglasvisitante = siglasvisitante;
        this.fecha = fecha;
    }

    public int getNjornada() {
        return njornada;
    }

    public void setNjornada(int njornada) {
        this.njornada = njornada;
    }

    

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getSiglaslocal() {
        return siglaslocal;
    }

    public void setSiglaslocal(String siglaslocal) {
        this.siglaslocal = siglaslocal;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getSiglasvisitante() {
        return siglasvisitante;
    }

    public void setSiglasvisitante(String siglasvisitante) {
        this.siglasvisitante = siglasvisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNpartido() {
        return npartido;
    }

    public void setNpartido(int npartido) {
        this.npartido = npartido;
    }

    @Override
    public String toString() {
        return "PartidoMostrar{" + "njornada=" + njornada + ", npartido=" + npartido + ", local=" + local + ", siglaslocal=" + siglaslocal + ", visitante=" + visitante + ", siglasvisitante=" + siglasvisitante + ", fecha=" + fecha + '}';
    }  
}
