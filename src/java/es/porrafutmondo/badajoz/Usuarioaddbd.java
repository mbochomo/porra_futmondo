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
public class Usuarioaddbd {
    private String id_usuario;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasena;

    public Usuarioaddbd(String id_usuario, String nombre, String apellidos, String correo, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id_usuario);
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.apellidos);
        hash = 41 * hash + Objects.hashCode(this.correo);
        hash = 41 * hash + Objects.hashCode(this.contrasena);
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
        final Usuarioaddbd other = (Usuarioaddbd) obj;
        if (!Objects.equals(this.id_usuario, other.id_usuario)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidos, other.apellidos)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuarioaddbd{" + "id_usuario=" + id_usuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", correo=" + correo + ", contrasena=" + contrasena + '}';
    }
    
    
}
