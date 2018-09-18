/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author marco
 */
@WebServlet(name = "Registro", urlPatterns = {"/RegistroUsuario"})
public class Registro extends HttpServlet {

    private BDUsuario bdu;

    public Registro() {
        super();
        bdu = new BDUsuario();
    }

    /**
     * @param config
     * @throws javax.servlet.ServletException
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        bdu.conexion();
    }

    /**
     * @see Servlet#destroy()
     */
    @Override
    public void destroy() {
        bdu.cerrarConexion();
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        Boolean existe;
        String id_usuario = request.getParameter("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String correo = request.getParameter("correo");
        String contra = request.getParameter("contra");
        if (id_usuario == null || nombre == null || apellidos == null || correo == null || contra == null) {
            response.sendRedirect("Registrar.html");
        } else {
            existe = bdu.comprobarSiExiste(id_usuario, correo);
            if (existe == true) {
                Usuarioaddbd U = new Usuarioaddbd(id_usuario, nombre, apellidos, correo, contra);
                if (bdu.addUsuario(U) == true) {
                    response.sendRedirect("inicio.jsp");
                }
                else{
                    response.sendRedirect("Registrar.html");
                }
            }
            else{
                response.sendRedirect("Registrar.html");
            }

        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
