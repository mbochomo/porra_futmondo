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
@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

    private BDUsuario bdu;

    /**
     * Constructor de la clase
     */
    public InicioSesion() {
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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InicioSesion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InicioSesion at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        response.sendRedirect("inicio.jsp");
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
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();
        String id_usuario = request.getParameter("usuario");
        String contra = request.getParameter("contra");

        if (bdu.inicioSesion(id_usuario, contra)) {
            System.out.println("Inicio de sesión correcto");
            sesion.setAttribute("usuario", id_usuario);
            if (id_usuario.equals("admin")) {
                response.sendRedirect("inicioadmin.jsp");
            } else {
                response.sendRedirect("inicio.jsp");
            }
        } else {
            System.out.println("Inicio de sesión no correcto");
            sesion.setAttribute("usuario", null);
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Inicio de sesión incorrecto');");
            out.println("</script>");
            response.sendRedirect("inicio.jsp");
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
