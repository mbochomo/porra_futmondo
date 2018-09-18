<%-- 
    Document   : clasificaciongeneral
    Created on : 14-ago-2018, 12:28:17
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.ArrayList"
        import="es.porrafutmondo.badajoz.JugadorClasificacionJornada"%>
<!DOCTYPE html>
<html>
    <head>
        <title>INICIO</title>
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
                         //   out.println("<a href='#'>Bienvenido " + us + "</a>");
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
            <table class ="tabla-apuesta">
                <tr>
                    <th>Pos.</th>
                    <th>Jugador</th>
                    <th>Aciertos totales</th>
                    <th>Puntos</th>
                    <th>Premio</th>
                </tr>
                <%
                    ArrayList<JugadorClasificacionJornada> clasificacion = (ArrayList) session.getAttribute("clasificacionjor");
                    int i = 1;
                    for(JugadorClasificacionJornada JC: clasificacion){
                        out.println("<tr>");
                        out.println("<th>"+i+"</th>");
                        out.println("<th>"+JC.getId_jugador()+"</th>");
                        out.println("<th>"+JC.getTotales()+"</th>");
                        out.println("<th>"+JC.getPuntos()+"</th>");
                        out.println("<th>  "+JC.getPremio()+"  €</th>");
                        out.println("</tr>");
                        i++;
                    }                    
                %>
            </table>
        </div>
    </body>
</html>