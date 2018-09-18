<%-- 
    Document   : insertar jornada
    Created on : 07-ago-2018, 11:41:04
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Insertar jornada</title>
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
                <h1><img src=balon.png alt="">Porra futmondo</h1>
                <input type="checkbox" id="btn-menu">
                <label for="btn-menu"><img src="icono_menu.png" alt=""></label>
                <nav class="menu">
                    <%
                         String us = (String) session.getAttribute("usuario");
                        if (us != null && us.equals("admin")) {
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
        <form action="insertarjornada" method="post" class="form-registro">
            <h2 class="form-titulo">Insertar partidos</h2>
            <div class="contenedor-inputs">
                <h4 class="add-partido-titulo">Partido 1:</h4>
                <select name="local1" class="inputs-chicos-reg" required>
                    <option selected value="">Local</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <select name="visitante1" class="inputs-chicos-reg" required>
                    <option selected value="">Visitante</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <input type="date" name="fecha1" class="inputs-chicos-reg" required>
                <input type="time" name="hora1" class="inputs-chicos-reg" required>
                <h4 class="add-partido-titulo">Partido 2:</h4>
                <select name="local2" class="inputs-chicos-reg" required>
                    <option selected value="">Local</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <select name="visitante2" class="inputs-chicos-reg" required>
                    <option selected value="">Visitante</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <input type="date" name="fecha2" class="inputs-chicos-reg" required>
                <input type="time" name="hora2" class="inputs-chicos-reg" required>
                <h4 class="add-partido-titulo">Partido 3:</h4>
                <select name="local3" class="inputs-chicos-reg" required>
                    <option selected value="">Local</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <select name="visitante3" class="inputs-chicos-reg" required>
                    <option selected value="">Visitante</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <input type="date" name="fecha3" class="inputs-chicos-reg" required>
                <input type="time" name="hora3" class="inputs-chicos-reg" required>
                <h4 class="add-partido-titulo">Partido 4:</h4>
                <select name="local4" class="inputs-chicos-reg" required>
                    <option selected value="">Local</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <select name="visitante4" class="inputs-chicos-reg" required>
                    <option selected value="">Visitante</option>
                    <optgroup label="La Liga"> 
                        <option value="Alaves">Alaves</option>
                        <option value="Athletic Club">Athletic Club</option>
                        <option value="Atletico de Madrid">Atletico de Madrid</option>
                        <option value="Celta">Celta</option>
                        <option value="Eibar">Eibar</option>
                        <option value="Espanyol">Espanyol</option>
                        <option value="FC Barcelona">FC Barcelona</option>
                        <option value="Getafe">Getafe</option>
                        <option value="Girona">Girona</option>
                        <option value="Huesca">Huesca</option>
                        <option value="Leganes">Leganes</option>
                        <option value="Levante">Levante</option>
                        <option value="Rayo Vallecano">Rayo Vallecano</option>
                        <option value="Real Betis">Real Betis</option>
                        <option value="Real Madrid">Real Madrid</option>
                        <option value="Real Sociedad">Real Sociedad</option>
                        <option value="Sevilla">Sevilla</option>
                        <option value="Valencia">Valencia</option>
                        <option value="Valladolid">Valladolid</option>
                        <option value="Villarreal">Villarreal</option>
                </select>
                <input type="date" name="fecha4" class="inputs-chicos-reg" required>
                <input type="time" name="hora4" class="inputs-chicos-reg" required>

                <input type="submit" value="Confirmar" class="btn-reg">
            </div>
        </form>
    </body>
</html>