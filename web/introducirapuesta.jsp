<%-- 
    Document   : introducirapuesta
    Created on : 09-ago-2018, 11:07:30
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Date" 
        import="java.text.SimpleDateFormat" 
        import="java.util.ArrayList" import="es.porrafutmondo.badajoz.PartidoMostrar"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Introducir mi apuesta</title>
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
                    <a href="#"><%
                        String us = (String) session.getAttribute("usuario");
                        if (us != null) {
                            out.println("<a href='introducirapuesta'>Jornada actual</a>");
                            out.println("<a href='consultarapuesta'>Consultar apuestas</a>");
                            out.println("<a href='ClasificacionGeneral'>Clasificación</a>");
                            out.println("<a href='ClasificacionJornada'>Clasificación jornada</a>");
                          //  out.println("<a href='#'>Bienvenido " + us+"</a>");
                            out.println("<a href='InicioSesion'>Cerrar sesión</a>");
                        } else {
                            response.sendRedirect("inicio.jsp");
                            out.println("<a href='Registrar.html'>Registrar</a>");
                            out.println("<a href='InicioSesion.html'>Iniciar Sesión</a>");
                        }
                        %></a>
                </nav>
            </div>
        </header>
        <div class="contenedor-res">
            <form action="introducirapuesta" method="post">
                <table class="tabla">                  
                    <%
                        SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat formatohora = new SimpleDateFormat("HH:mm");
                        ArrayList<PartidoMostrar> lista = (ArrayList) session.getAttribute("partidos_jornada");
                        if (lista == null) {
                            response.sendRedirect("inicio.jsp");
                        }
                        int i = 1;
                        for (PartidoMostrar P : lista) {
                            out.println("<tr>");
                            out.println("<th><img src=Escudos/" + P.getSiglaslocal() + ".png alt='' height='40px' width='40px'></th>");
                            out.println("<th> " + P.getLocal() + " </th>");
                            //out.println("<th><input type='text' name='goleslocal" + i + "' id='goleslocal" + i + "' style='width : 1px; heigth : 1px' onsubmit='return validacion()' required></th>");
                            //Apuesta local
                            out.println("<th><select name='goleslocal"+i+"' style='width:35px; height: 35px;' required>");
                            out.println("<option selected value=''>-</option>");
                            for (int j = 0; j<10; j++){
                                out.println("<option value="+j+">"+j+"</option>");
                            }
                            //Apuesta visitante
                            out.println("</select></th>");
                            out.println("<th><select name='golesvisitante"+i+"' style='width:35px; height: 35px;' required>");
                            out.println("<option selected value=''>-</option>");
                            for (int j = 0; j<10; j++){
                                out.println("<option value="+j+">"+j+"</option>");
                            }
                            out.println("</select></th>");
//out.println("<th><input type='text' class='input-res' name='golesvisit" + i + "' id='golesvisitante" + i + "' onsubmit='return validacion()'required></th>");
                            out.println("<th> " + P.getVisitante() + " </th>");
                            out.println("<th><img src=Escudos/" + P.getSiglasvisitante() + ".png alt='' height='40px' width='40px'></th>");
                            out.println("</tr");
                            out.println("<tr>");
                            out.println("<th></th>");
                            String fecha = formatofecha.format(P.getFecha());
                            String hora = formatohora.format(P.getFecha());
                            out.println("<th>Fecha: " + fecha + " </th>");
                            out.println("<th></th>");
                            out.println("<th></th>");
                            out.println("<th>Hora: " + hora + "</th>");
                            out.println("<th></th>");
                            out.println("</tr>");
                            i++;
                        }
                    %>
                    <tr>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th><input type="submit" value="Aceptar" class="btn-aceptar"></th>
                        <th></th>
                    </tr>
                </table>                       
            </form>
        </div>
    </body>
</html>
