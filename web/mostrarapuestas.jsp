<%-- 
    Document   : mostrarapuestas
    Created on : 12-ago-2018, 13:04:39
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList"
        import="es.porrafutmondo.badajoz.PartidoMostrar" import="es.porrafutmondo.badajoz.JugadorApuesta"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Mostrar apuestas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/mostrarapuestas.css">
        <link rel="stylesheet" type="text/css" href="css/menu.css">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
        <link rel="stylesheet" type="text/css" href="css/formularios.css">
    </head>
    <body>
        <header>
            <div class ="contenedor">
                <h1><a href="inicio.jsp" class="titulo-cabecera" style="text-decoration:none; color: #000000"><img src=balon.png alt="">Porra futmondo</a></h1>
                <input type="checkbox" id="btn-menu">
                <label for="btn-menu"><img src="icono_menu.png" alt=""></label>
                <nav class="menu">
                    <%
                        String us = (String) session.getAttribute("usuario");
                        if (us != null) {
                            out.println("<a href='introducirapuesta'>Jornada actual</a>");
                            out.println("<a href='consultarapuesta'>Consultar apuestas</a>");
                            out.println("<a href='ClasificacionGeneral'>Clasificación</a>");
                            out.println("<a href='ClasificacionJornada'>Clasificación jornada</a>");
                          //  out.println("<a href='#'>Bienvenido " + us + "</a>");
                            out.println("<a href='InicioSesion'>Cerrar sesión</a>");
                        } else {
                            out.println("<a href='Registrar.html'>Registrar</a>");
                            out.println("<a href='InicioSesion.html'>Iniciar Sesión</a>");
                        }
                    %>
                </nav>
            </div>
        </header>
        <div class="contenedor-res">
            <table class="tabla-apuesta">
                <tr>
                    <th>Jugadores\Partidos</th>
                        <%
                            ArrayList<PartidoMostrar> listapartidos = (ArrayList) session.getAttribute("listapartidos");
                            for (PartidoMostrar PM : listapartidos) {
                                //out.println("<th>"+PM.getSiglaslocal()+"-"+PM.getSiglasvisitante()+"</th>");
                                out.println("<th><img src=Escudos/" + PM.getSiglaslocal() + ".png alt='' height='40px' width='40px'><img src=Escudos/" + PM.getSiglasvisitante() + ".png alt='' height='40px' width='40px'></th>");
                            }
                        %>
                </tr>
                    <%
                           ArrayList<JugadorApuesta> listaapuestas = (ArrayList) session.getAttribute("listaapuestas");
                           for(JugadorApuesta JA: listaapuestas){
                               out.println("<tr>");
                               out.println("<th>"+JA.getId_jugador()+"</th>");
                               for (int i = 0; i<JA.getApuestas().length; i++){
                                   if (JA.getApuestas()[i] != null){
                                       out.println("<th>"+JA.getApuestas()[i].getGoles_local()+"-"+JA.getApuestas()[i].getGoles_visitante()+"</th>");
                                   }
                                   else{
                                       out.println("<th>-</th>");
                                   }
                               }
                               out.println("</tr>");
                           }
                    %>
            </table>
        </div>
    </body>
</html>
