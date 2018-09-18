/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author marco
 */
public class BDUsuario {

    private Connection cn;

    public BDUsuario() {
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
     * Método que comprueba si un usuario existe o no. Para ello comprobará si
     * está el correo o el id_usuario en la base de datos.
     *
     * @param usuario
     * @param correo
     * @return
     */
    public Boolean comprobarSiExiste(String usuario, String correo) {
        String query = "select id_jugador from jugador where id_jugador='" + usuario + "' or correo='" + correo + "'";
        ResultSet rs = consultaBD(query); //Se realiza la consulta
        try {
            if (!rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Método que introduce el usuario en la BD.
     *
     * @param U
     * @return
     */
    public Boolean addUsuario(Usuarioaddbd U) {
        String C = Encriptar(U.getContrasena());
        String query = "insert into jugador (id_jugador, nombre, apellidos, correo, contrasena,futmondo) values (?, ?, ?, ?, ?,'s')";
        PreparedStatement st;
        try {
            st = cn.prepareStatement(query);
            st.setString(1, U.getId_usuario());
            st.setString(2, U.getNombre());
            st.setString(3, U.getApellidos());
            st.setString(4, U.getCorreo());
            st.setString(5, C);
            st.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Boolean inicioSesion(String usuario, String contra) {
        ResultSet rs;
        String query = "select contrasena from jugador where id_jugador ='" + usuario + "'";
        System.out.println(query);
        rs = consultaBD(query);
        try {
            if (rs.next()) {
                String contraBD = rs.getString(1);
                String c = Desencriptar(contraBD);
                if (c.equals(contra)) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BDUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("False");
        return false;
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
    
    public static String Encriptar(String texto) {
        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
    public static String Desencriptar(String textoEncriptado) throws Exception {

        String secretKey = "qualityinfosolutions"; //llave para desenciptar datos
        String base64EncryptedString = "";

        try {
            byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");

            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);

            byte[] plainText = decipher.doFinal(message);

            base64EncryptedString = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
}
