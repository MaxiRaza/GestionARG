<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="tipo_proveedor" class="Modelo.Tipo_Proveedor" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} tipo de  Proveedor</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} tipo de  Proveedor</h1><br>
        <form method="Post" action="Tipo_Proveedores">
            <div class="container col-md-4">                  
                <input type="hidden" name="txtIdTipoProveedor" value="<jsp:getProperty name="tipo_proveedor" property="id_tipo_proveedor"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="tipo_proveedor" property="nombre"></jsp:getProperty>" </c:if>>
                        </div><br>
                <jsp:include page="../Componentes\botonesAM.jsp"/>      
            </div>
        </form>
    </body>
</html>