<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="tipo_cliente" class="Modelo.Tipo_Cliente" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} tipo de  Cliente</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} tipo de  Cliente</h1><br>
        <form method="Post" action="Tipo_Clientes">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdTipoCliente" value="<jsp:getProperty name="tipo_cliente" property="id_tipo_cliente"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="tipo_cliente" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>                                                  
                    </div><br>
                    <div class="row justify-content-md-center" >
                        <a href="Tipo_Clientes" class="btn btn-primary">Volver al listado</a>
                        <button type="submit" class="btn btn-success">${accion} tipo de Cliente</button>  
            </div>
        </form>
    </body>
</html>
