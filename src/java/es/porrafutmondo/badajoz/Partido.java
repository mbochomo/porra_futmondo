/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.util.Objects;

/**
 *
 * @author marco
 */
public class Partido {
    private int njornada;
    private int npartido;
    private String local;
    private String visitante;
    private String fecha;
    private String hora;
    private String resultado;
    
    public Partido(){
        njornada = 0;
        npartido = 0;
        local = "";
        visitante = "";
        fecha = "";
        hora = "";
        resultado = "";
    }
    public Partido(int njornada, int npartido, String local, String visitante, String fecha, String hora, String resultado) {
        this.njornada = njornada;
        this.npartido = npartido;
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.hora = hora;
        this.resultado = resultado;
    }

    public Partido(int njornada, int npartido, String local, String visitante, String fecha, String hora) {
        this.njornada = njornada;
        this.npartido = npartido;
        this.local = local;
        this.visitante = visitante;
        this.fecha = fecha;
        this.hora = hora;
        this.resultado = null;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partido other = (Partido) obj;
        if (this.njornada != other.njornada) {
            return false;
        }
        if (this.npartido != other.npartido) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.visitante, other.visitante)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        if (!Objects.equals(this.resultado, other.resultado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Partido{" + "njornada=" + njornada + ", npartido=" + npartido + ", local=" + local + ", visitante=" + visitante + ", fecha=" + fecha + ", hora=" + hora + ", resultado=" + resultado + '}';
    }
    
    
}
