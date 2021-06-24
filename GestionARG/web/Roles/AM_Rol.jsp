<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="roll" class="Modelo.Rol" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Rol</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Rol</h1><br>
        <form method="Post" action="Roles">
            <div class="container col-md-4">                  
                <input type="hidden" name="txtIdRol" value="<jsp:getProperty name="roll" property="id_rol"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="roll" property="nombre"></jsp:getProperty>" </c:if>>
                        </div><br>
                <jsp:include page="../Componentes\botonesAM.jsp"/>   
            </div>
        </form>
    </body>
</html>