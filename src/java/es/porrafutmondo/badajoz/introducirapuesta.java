/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.porrafutmondo.badajoz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
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
@WebServlet(name = "introducirapuesta", urlPatterns = {"/introducirapuesta"})
public class introducirapuesta extends HttpServlet {
    private PartidosBD bdp;
    
    /**
     * Constructor
     */
    public introducirapuesta() {
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
            out.println("<title>Servlet introducirapuesta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet introducirapuesta at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("introducirapuesta.jsp");    
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
        PrintWriter out = response.getWriter();
        String jugador = (String) sesion.getAttribute("usuario");
        Date D = new Date();
        ArrayList <PartidoMostrar> lista = (ArrayList) sesion.getAttribute("partidos_jornada");
        String mensajeNoIntroducido = "";
        Boolean mensaje = false;
        Boolean correcto = false;
        int i = 1;
        for (PartidoMostrar P: lista){
            if (D.before(P.getFecha())){
                String recogelocal = "goleslocal"+i;
                String recogevisit = "golesvisitante"+i;
                int goles_local = Integer.parseInt(request.getParameter(recogelocal));
                int goles_visit = Integer.parseInt(request.getParameter(recogevisit));
                Apuesta ap = new Apuesta(P.getNjornada(), P.getNpartido(), jugador, goles_local, goles_visit, 0);
                out.println(ap.toString());
                i++;
                correcto = bdp.addApuesta(ap);
            }
            else{
                mensajeNoIntroducido = mensajeNoIntroducido+"La apuesta del partido "+P.getLocal()+" vs "+P.getVisitante()+" no se ha introducido porque ya ha empezado o ya ha sido jugado\n";
                mensaje = true;
            }
        }
        if (correcto == true){
            sesion.setAttribute("mensaje_introducido", "Apuestas guardadas correctamente");
            out.println("Apuesta introducida correctamente.");
            response.sendRedirect("inicio.jsp");
        }
        else{
            sesion.setAttribute("mensaje_introducido", "Apuestas no guardadas correctamente");
            out.println("Apuesta no introducida correctamente");
            response.sendRedirect("introducirapuesta.jsp");
        }
        if (mensaje = true){
            out.println(mensajeNoIntroducido);
            sesion.setAttribute("mensaje_partidopasado", mensajeNoIntroducido);
        }
        //response.sendRedirect("inicio.jsp");
       // response.sendRedirect("apuestarealizada.jsp");
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
