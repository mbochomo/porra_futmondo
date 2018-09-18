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
@WebServlet(name = "ComprobarResultados", urlPatterns = {"/ComprobarResultados"})
public class ComprobarResultados extends HttpServlet {

    private PartidosBD bdp;

    /**
     * Constructor
     */
    public ComprobarResultados() {
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
            out.println("<title>Servlet ComprobarResultados</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComprobarResultados at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        HttpSession sesion = request.getSession();
        ArrayList<PartidoMostrar> lista = bdp.getJornada();
        sesion.setAttribute("partidos_jornada", lista);
        response.sendRedirect("introducirresultados.jsp");
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
        ArrayList<PartidoMostrar> lista = (ArrayList) sesion.getAttribute("partidos_jornada");
        //ArrayList<String> lista_jugadores = bdp.retornarJugadores();
        int njornada = bdp.getJornadaActual().getNjornada();
        int i = 1;
        for (PartidoMostrar P : lista) {
            String recogelocal = "goleslocal" + i;
            String recogevisit = "golesvisitante" + i;
            int goles_local = Integer.parseInt(request.getParameter(recogelocal));
            int goles_visit = Integer.parseInt(request.getParameter(recogevisit));
            out.println(P.getLocal()+" "+goles_local+" - "+goles_visit+" "+P.getVisitante());
            bdp.comprobarApuestas(goles_local, goles_visit, i, njornada);
            i++;
        }
        response.sendRedirect("inicioadmin.jsp");
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
