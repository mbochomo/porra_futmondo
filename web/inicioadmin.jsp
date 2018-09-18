<%-- 
    Document   : inicio
    Created on : 05-ago-2018, 12:34:30
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>INICIO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/fontello.css">
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
                            out.println("<a href='insertarjornada.jsp'>Nueva jornada</a>");
                            out.println("<a href='ComprobarResultados'>Cerrar jornada</a>");
                            out.println("<a href='#'>Clasificación</a>");
                            out.println("<a href='#'>Bienvenido " + us+"</a>");
                            out.println("<a href='InicioSesion'>Cerrar sesión</a>");
                        } else {
                            out.println("<a href='Registrar.html'>Registrar</a>");
                            out.println("<a href='InicioSesion.html'>Iniciar Sesión</a>");
                        }
                    %>
                </nav>
            </div>
        </header>
    </body>
</html>
