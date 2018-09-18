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
@WebServlet(name = "insertarjornada", urlPatterns = {"/insertarjornada"})
public class insertarjornada extends HttpServlet {

    private PartidosBD bdp;

    /**
     * Constructor
     */
    public insertarjornada() {
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
            out.println("<title>Servlet insertarjornada</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet insertarjornada at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        ArrayList<Partido> lista = new ArrayList();
        Partido P = new Partido();

        //Cambiar lo de la jornada
        Jornada J = bdp.getJornadaActual();
        if (J.getFinalizada() == true) {
            int jornada = J.getNjornada()+1;
            J.setNjornada(jornada);
            P.setNjornada(jornada);
            P.setNpartido(1);
            P.setLocal(request.getParameter("local1"));
            P.setVisitante(request.getParameter("visitante1"));
            P.setFecha(request.getParameter("fecha1"));
            P.setHora(request.getParameter("hora1"));
            lista.add(P);

            Partido P2 = new Partido();
            P2.setNjornada(jornada);
            P2.setNpartido(2);
            P2.setLocal(request.getParameter("local2"));
            P2.setVisitante(request.getParameter("visitante2"));
            P2.setFecha(request.getParameter("fecha2"));
            P2.setHora(request.getParameter("hora2"));
            lista.add(P2);

            Partido P3 = new Partido();
            P3.setNjornada(jornada);
            P3.setNpartido(3);
            P3.setLocal(request.getParameter("local3"));
            P3.setVisitante(request.getParameter("visitante3"));
            P3.setFecha(request.getParameter("fecha3"));
            P3.setHora(request.getParameter("hora3"));
            lista.add(P3);

            Partido P4 = new Partido();
            P4.setNjornada(jornada);
            P4.setNpartido(4);
            P4.setLocal(request.getParameter("local4"));
            P4.setVisitante(request.getParameter("visitante4"));
            P4.setFecha(request.getParameter("fecha4"));
            P4.setHora(request.getParameter("hora4"));
            lista.add(P4);
            
            bdp.insertarPartidos(lista);
            bdp.actualizarJornada(J); 
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
