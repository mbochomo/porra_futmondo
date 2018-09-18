/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class PartidosBD {

    private Connection cn;

    public PartidosBD() {
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
     *
     * @param lista
     */
    public void insertarPartidos(ArrayList<Partido> lista) {
        lista.forEach((P) -> {//Aquí había un for
            String query = "insert into partido (njornada, npartido, equipolocal, equipovisitante, fecha, hora) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement st;
            try {
                st = cn.prepareStatement(query);
                st.setInt(1, P.getNjornada());
                st.setInt(2, P.getNpartido());
                st.setString(3, P.getLocal());
                st.setString(4, P.getVisitante());
                st.setString(5, P.getFecha());
                st.setString(6, P.getHora());
                st.execute();
            } catch (SQLException ex) {
                Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    /**
     * Actualiza la jornada cuando se inserta una nueva. Se actualiza el número
     * de la misma y el parámetro "finalizada" se actualiza a false.
     * @param J 
     */
    public void actualizarJornada(Jornada J) {
        try {
            String query = "update jornadactual "
                    + "set njornada = ?";
            PreparedStatement st;
            st = cn.prepareStatement(query);
            st.setInt(1, J.getNjornada());
            st.execute();
            query = "update jornadactual set finalizada=?";
            st = cn.prepareStatement(query);
            st.setString(1, "f");
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * Retorna la lista de los partidos de la jornada.
     *
     * @return
     */
    public ArrayList<PartidoMostrar> getJornada() {
        ArrayList<PartidoMostrar> lista = new ArrayList();
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //Búsqueda de la jornada actual y si está cerrada o no
        Jornada J = getJornadaActual();
        if (J.getFinalizada() == false) {
            //Consulta de los partidos de la jornada
            String query = "select * from partido where njornada =" + J.getNjornada() + " order by fecha, hora ASC";
            ResultSet rs = consultaBD(query);
            try {
                while (rs.next()) {
                    int njornada = rs.getInt(1);
                    int npartido = rs.getInt(2);
                    String local = rs.getString(3);
                    String siglaslocal = getSiglas(local);
                    String visitante = rs.getString(4);
                    String siglasvisit = getSiglas(visitante);
                    String fecha = rs.getString(5);
                    String hora = rs.getString(6);
                    String fecha_completa = fecha + " " + hora;
                    Date D = formatofecha.parse(fecha_completa);
                    PartidoMostrar P = new PartidoMostrar(njornada, npartido, local, siglaslocal, visitante, siglasvisit, D);
                    lista.add(P);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    /**
     * Consulta las siglas del equipo deseado.
     *
     * @param equipo
     * @return
     */
    public String getSiglas(String equipo) {
        String query = "select siglas from equipos where nombre_completo='" + equipo + "'";
        ResultSet rs = consultaBD(query);
        String siglas = new String();
        try {
            rs.next();
            siglas = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return siglas;
    }

    /**
     * Añade las apuestas de un jugador a la base de datos
     *
     * @param ap
     * @return
     */
    public Boolean addApuesta(Apuesta ap) {
        String query = "insert into apuesta (njornada, npartido, id_jugador, goles_local, goles_visitante) values (?, ?, ?, ?, ?)";

        PreparedStatement st;
        try {
            System.out.println(ap.toString());
            st = cn.prepareStatement(query);
            st.setInt(1, ap.getNjornada());
            st.setInt(2, ap.getNpartido());
            st.setString(3, ap.getId_jugador());
            st.setInt(4, ap.getGoles_local());
            st.setInt(5, ap.getGoles_visitante());
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    /**
     * Devuelve una lista del objeto JugadorApuesta que relaciona el jugador con
     * las apuestas que ha realizado
     *
     * @param njornada
     * @return
     */
    public ArrayList<JugadorApuesta> getApuestas(int njornada) {
        ArrayList<JugadorApuesta> lista = new ArrayList();
        String query = "select id_jugador from jugador";
        ResultSet rs = consultaBD(query);
        try {
            while (rs.next()) {
                String id_jugador = rs.getString(1);
                String consulta = "select * from apuesta where id_jugador = '" + id_jugador + "' and njornada = " + njornada;
                ResultSet rs2 = consultaBD(consulta);
                JugadorApuesta JA = new JugadorApuesta(id_jugador);
                if (!id_jugador.equals("admin")) {
                    while (rs2.next()) {
                        int npartido = rs2.getInt(2);
                        int goleslocal = rs2.getInt(4);
                        int golesvisit = rs2.getInt(5);
                        Apuesta A = new Apuesta(njornada, npartido, id_jugador, goleslocal, golesvisit, 0);
                        JA.setPosApuestas(A, npartido - 1);
                    }
                    lista.add(JA);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * Método que devuelve una lista con los identificadores de usuario
     *
     * @return
     */
    public ArrayList<String> retornarJugadores() {
        String query = "select id_usuario from jugador";
        ResultSet rs = consultaBD(query);
        ArrayList<String> lista = new ArrayList();

        try {
            while (rs.next()) {
                String id_usuario = rs.getString(1);
                lista.add(id_usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    /**
     * Comprobación de las apuestas. Para ello se consultan las apuestas en la
     * base de datos
     *
     * @param golesLocal goles del equipo local en el partido
     * @param golesVisitante goles del equipo visitante en el partido
     * @param npartido Número del partido que se comprueba
     * @param njornada Jornada que se comprueba
     */
    public void comprobarApuestas(int golesLocal, int golesVisitante, int npartido, int njornada) {
        String query = "select * from apuesta where njornada = " + njornada + " and npartido = " + npartido;
        System.out.println(query);
        ResultSet rs = consultaBD(query);

        try {
            while (rs.next()) {
                String id_jugador = rs.getString(3);
                int apuestalocal = rs.getInt(4);
                int apuestavisit = rs.getInt(5);
                int puntos = contadorpuntos(golesLocal, golesVisitante, apuestalocal, apuestavisit);
                String update = "update apuesta set puntos = " + puntos + " where njornada = " + njornada + " and npartido = " + npartido + " and id_jugador = '" + id_jugador + "'";
                PreparedStatement st;
                st = cn.prepareStatement(update);
                st.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Se calculan los puntos obtenidos por un jugador en un partido
     *
     * @param golesLocal goles del equipo local en el partido
     * @param golesVisit goles del equipo visitante en el partido
     * @param apuestaLocal apuesta del equipo local
     * @param apuestaVisit apuesta del equipo visitante
     * @return puntos obtenidos por el jugador
     */
    public int contadorpuntos(int golesLocal, int golesVisit, int apuestaLocal, int apuestaVisit) {
        int contador = 0;
        int diferenciapartido = golesLocal - golesVisit;
        int diferenciaapuesta = apuestaLocal - apuestaVisit;
        if (golesLocal == apuestaLocal && golesVisit == apuestaVisit) {
            return 10;
        } else {
            if (golesLocal == apuestaLocal || golesVisit == apuestaVisit) {
                contador += 3;
            }
            if (diferenciaapuesta == diferenciapartido) {
                contador += 3;
            }
            if (diferenciaapuesta > 0 && diferenciapartido > 0) {
                contador += 3;
            } else if (diferenciaapuesta < 0 && diferenciapartido < 0) {
                contador += 3;
            } else if (diferenciaapuesta == 0 && diferenciapartido == 0) {
                contador += 3;
            }
        }
        return contador;
    }
    /**
     * Una vez finalizada una jornada, se actualiza el parámetro finalizada a
     * true.
     * @param J 
     */
    public void actualizarJornadaFin(Jornada J) {
        try {
            String query = "update jornadactual "
                    + "set njornada = ?";
            PreparedStatement st;
            st = cn.prepareStatement(query);
            st.setInt(1, J.getNjornada());
            st.execute();
            query = "update jornadactual set finalizada=?";
            st = cn.prepareStatement(query);
            st.setString(1, "f");
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PartidosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
