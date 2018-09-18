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
public class JugadorApuesta {

    private String id_jugador;
    private Apuesta[] apuestas = new Apuesta[4];

    public JugadorApuesta(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public String getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(String id_jugador) {
        this.id_jugador = id_jugador;
    }

    public Apuesta[] getApuestas() {
        return apuestas;
    }

    public void setApuestas(Apuesta[] apuestas) {
        this.apuestas = apuestas;
    }

    @Override
    public String toString() {
        String cad = "";
        for (int i = 0; i < apuestas.length; i++) {
            if (apuestas[i] != null) {
                cad = cad + apuestas[i].toString() + "\n";
            } else {
                cad = cad + "null\n";
            }
        }
        return "JugadorApuesta{" + "id_jugador=" + id_jugador + ", apuestas=" + cad + '}';
    }

    public void setPosApuestas(Apuesta a, int indice) {
        apuestas[indice] = a;
    }

}
