/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ClasificacionBD {

    private Connection cn;

    public ClasificacionBD() {
        cn = null;
    }

    /**
     * Conexión a la base de datos
     *
     * @return
     */
    public Boolean conexion() {
        try {
            String url = "jdbc:mysql://localhost/porra_futmondo";
            @SuppressWarnings("unused")
            Object newInstance = Class.forName("com.mysql.jdbc.Driver").newInstance();
            cn = DriverManager.getConnection(url, "marco", "gatitus");
            @SuppressWarnings("unused")
            Statement instruccion;
            instruccion = cn.createStatement();
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Conexión no realizada. " + ex.getMessage());
            return false;
        }
        System.out.println("Conexión realizada");
        return true;
    }

    /**
     * Método que cierra la conexión con la base de datos
     */
    public void cerrarConexion() {
        try {
            cn.close();
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        System.out.println("Conexión cerrada");
    }

    /**
     * Método que realiza una consulta a la BD.
     *
     * @param query
     * @return
     */
    public ResultSet consultaBD(String query) {
        ResultSet rs = null;
        Statement st;
        try {
            st = cn.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Excepcion");
            ex.printStackTrace();
        }
        return rs;
    }

    public ArrayList<JugadorClasificacion> realizarClasificacion() {
        ArrayList<JugadorClasificacion> clasificacion = new ArrayList();
        String query = "select id_jugador, sum(puntos) from apuesta group by id_jugador";
        ResultSet rs = consultaBD(query);
        try {
            while (rs.next()) {
                String id_jugador = rs.getString(1);
                int puntos = rs.getInt(2);
                String consulta_totales = "select count(id_jugador) from apuesta where id_jugador = '" + id_jugador + "' and puntos=10";
                ResultSet rs2 = consultaBD(consulta_totales);
                int totales = 0;
                while (rs2.next()) {
                    try {
                        totales = rs2.getInt(1);
                    } catch (NullPointerException ex) {
                        totales = 0;
                    }
                }
                JugadorClasificacion JC = new JugadorClasificacion(id_jugador, puntos, totales);
                clasificacion.add(JC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasificacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(clasificacion);
        return clasificacion;
    }

    /**
     * Retorna datos de la jornada actual
     *
     * @return
     */
    public Jornada getJornadaActual() {
        ResultSet rs = consultaBD("select * from jornadactual");
        Jornada J = null;
        try {
            rs.next();
            String cad = rs.getString(2);
            if (cad.equals("t")) {
                J = new Jornada(rs.getInt(1), true);
            } else {
                J = new Jornada(rs.getInt(1), false);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return J;
    }

    public ArrayList<JugadorClasificacionJornada> realizarClasificacionJornada(int J) {
        ArrayList<JugadorClasificacionJornada> clasificacion = new ArrayList();
        String query = "select id_jugador, sum(puntos) from apuesta where njornada = " + J + " group by id_jugador";
        ResultSet rs = consultaBD(query);
        try {
            while (rs.next()) {
                String id_jugador = rs.getString(1);
                int puntos = rs.getInt(2);
                String consulta_totales = "select count(id_jugador) from apuesta where id_jugador = '" + id_jugador + "' and puntos=10 and njornada = " + J;
                ResultSet rs2 = consultaBD(consulta_totales);
                int totales = 0;
                while (rs2.next()) {
                    try {
                        totales = rs2.getInt(1);
                    } catch (NullPointerException ex) {
                        totales = 0;
                    }
                }
                JugadorClasificacionJornada JC = new JugadorClasificacionJornada(id_jugador, puntos, totales);
                clasificacion.add(JC);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasificacionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        Collections.sort(clasificacion);
        int puntos = 0;
        int premios[] = {3000000, 2000000, 1000000};
        int indice = -1;
        for (JugadorClasificacionJornada JC : clasificacion) {
            String consulta = "select futmondo from jugador where id_jugador = '" + JC.getId_jugador()+"'";
            ResultSet rs3 = consultaBD(consulta);
            try {
                rs3.next();
                String futmondo = rs3.getString(1);
                if (futmondo.equals("s")) {
                    if (indice < 3) {
                        if (JC.getPuntos() == puntos) {
                            JC.setPremio(premios[indice]);
                        }
                        else{
                            indice++;
                            if (indice < 3){
                                JC.setPremio(premios[indice]);
                            }
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(ClasificacionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return clasificacion;
    }

}
