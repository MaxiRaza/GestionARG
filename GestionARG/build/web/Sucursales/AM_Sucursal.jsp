<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="sucursal" class="Modelo.Sucursal" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Sucursal</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Sucursal</h1><br>
        <form method="Post" action="Sucursales">
            <div class="container col-md-4">                  
                <input type="hidden" name="txtIdSucursal" value="<jsp:getProperty name="sucursal" property="id_sucursal"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="sucursal" property="nombre"></jsp:getProperty>" </c:if>>
                        </div>                                           
                        <div class="form-group">
                            <label class="col-form-label">Dirección (*)</label>
                            <input type="text" name="txtDireccion" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="sucursal" property="direccion"></jsp:getProperty>" </c:if>>
                        </div><br>
                <jsp:include page="../Componentes\botonesAM.jsp"/>      
            </div>
        </form>
    </body>
</html>