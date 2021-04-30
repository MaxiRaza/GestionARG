<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Rol" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Registrar Rol</title>
    </head>
    <body>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <div>
            <h1>Registrar un Rol</h1>
        </div>
        <div> 
            <form method="Post" action="Roles">
                <input type="hidden" name="txtIdRol" value="0" />
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Nombre</label>
                    <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name" >
                </div>           
                <div>
                    <a href="Roles" class="btn bg-primary">Volver al listado</a>
                    <button type="submit" class="btn btn-success moverI">Registrar Rol</button>             
                </div>    
            </form>       
        </div>
    </body>
</html>
