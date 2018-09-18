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

/**
 *
 * @author marco
 */
@WebServlet(name = "ClasificacionJornada", urlPatterns = {"/ClasificacionJornada"})
public class ClasificacionJornada extends HttpServlet {

    private ClasificacionBD CBD;

    public ClasificacionJornada() {
        CBD = new ClasificacionBD();
    }

    /**
     * @param config
     * @throws javax.servlet.ServletException
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        CBD.conexion();
    }

    /**
     * @see Servlet#destroy()
     */
    @Override
    public void destroy() {
        CBD.cerrarConexion();
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
            out.println("<title>Servlet ClasificacionJornada</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ClasificacionJornada at " + request.getContextPath() + "</h1>");
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
        Jornada J = CBD.getJornadaActual();
        if (J.getFinalizada() == true) {
            ArrayList<JugadorClasificacionJornada> clasificacion = CBD.realizarClasificacionJornada(J.getNjornada());
            request.getSession().setAttribute("clasificacionjor", clasificacion);
            response.sendRedirect("clasificacionjornada.jsp");
        } else {
            if (J.getNjornada() > 1) {
                int jornada = J.getNjornada() - 1;
                ArrayList<JugadorClasificacionJornada> clasificacion = CBD.realizarClasificacionJornada(jornada);
                request.getSession().setAttribute("clasificacionjor", clasificacion);
                response.sendRedirect("clasificacionjornada.jsp");
                //Se busca
            }
        }

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
        processRequest(request, response);
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
