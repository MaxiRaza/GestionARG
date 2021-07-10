<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="CSS/Loginn.css" rel="stylesheet" type="text/css"/>
        <title>GestionARG - Inicio Sesion</title>
    </head>  
    <body>             
        <div class="body"></div>
        <div class="header">
            <div>Gestion<span>ARG</span></div>
        </div>
        <div class="login">   
            <% if (request.getAttribute("mensajeError") != null && !request.getAttribute("mensajeError").equals("")) { %>
            <h2>${mensajeError}</h2>
            <% }%> 
            <form method="Post" action="Loginn" >
                <input type="hidden" name="log" value="${log}"/>
                <div>
                    <input type="text" name="txtAlias" placeholder="Usuario" required="required" value="${alias}"/>
                </div>
                <div class="campo">
                    <input type="password" id="password" name="txtContrasenia" placeholder="Contraseña" required="required" value="${contrasenia}"/>
                    <span>Mostrar</span>
                </div>
                <button type="submit" class="btn btn-primary btn-block btn-large">Iniciar Sesion</button><br>           
            </form>
            <a href="Loginn?modo=registrarse" class="btn btn-primary btn-block btn-large">Registrarse</a><br>
            <a style="font-size: 18px; color: blue;" href="Loginn?modo=recuperacion">Olvide mi contraseña</a>
        </div>
        <script  type="text/javascript" src="JS/Funcionalidad.js"></script>
    </body>
</html>