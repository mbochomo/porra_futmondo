/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "consultarapuesta", urlPatterns = {"/consultarapuesta"})
public class consultarapuesta extends HttpServlet {
    private PartidosBD bdp;

    /**
     * Constructor
     */
    public consultarapuesta() {
        bdp = new PartidosBD();
    }

    /**
     * @param config
     * @throws javax.servlet.ServletException
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        bdp.conexion();
    }

    /**
     * @see Servlet#destroy()
     */
    @Override
    public void destroy() {
        bdp.cerrarConexion();
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
            out.println("<title>Servlet consultarapuesta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet consultarapuesta at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Jornada J = bdp.getJornadaActual();
        ArrayList<PartidoMostrar> listapartidos = bdp.getJornada();
        ArrayList<JugadorApuesta> listaapuestas = bdp.getApuestas(J.getNjornada());
        sesion.setAttribute("listapartidos", listapartidos);
        sesion.setAttribute("listaapuestas", listaapuestas);
        response.sendRedirect("mostrarapuestas.jsp");
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
