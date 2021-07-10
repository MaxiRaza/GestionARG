<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Recuperar Contraseña</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">Recuperar Contraseña</h1><br>
        <form method="Post" action="Loginn">
            <div class="container col-md-4">                  
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Ingrese su Correo (*)</label>
                    <input type="email" name="txtCorreo" required="required" class="form-control" id="recipient-name" >
                </div><br>                       
            </div>       
            <div class="row justify-content-md-center" >
                <div class="col-sm-1">
                    <a href="Loginn?modo=iniciarSesion" class="btn btn-primary" style="width: 120px"><i class="bi bi-reply-fill" style="font-size: 22px"></i></a>
                </div>  
                <div class="col-sm-1">
                    <button type="submit" class="btn btn-success" style="width: 120px; display: ${confirmar} "><i class="bi bi-save2" style="font-size: 22px"></i></button>  
                </div>  
            </div>
        </form>
    </body>
</html>