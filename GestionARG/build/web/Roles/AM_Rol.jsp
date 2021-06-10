<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="rol" class="Modelo.Rol" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Rol</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Rol</h1><br>
        <form method="Post" action="Roles">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdRol" value="<jsp:getProperty name="rol" property="id_rol"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="rol" property="nombre"></jsp:getProperty>" </c:if>>
                        </div>                                                  
                    </div><br>
                    <div class="row justify-content-md-center" >
                        <a href="Roles" class="btn btn-primary">Volver al listado</a>
                        <button type="submit" class="btn btn-success">${accion} Rol</button>  
            </div>
        </form>
    </body>
</html>