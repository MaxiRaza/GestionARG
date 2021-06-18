<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="deposito" class="Modelo.Deposito" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Depósito</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Depósito</h1><br>
        <form method="Post" action="Depositos">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdRol" value="<jsp:getProperty name="deposito" property="id_deposito"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Ubicación (*)</label>
                        <input type="text" name="txtUbicacion" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="deposito" property="ubicacion"></jsp:getProperty>" </c:if>>
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Sucursal (*)</label>
                            <select name="cmbSucursales" required="required" class="form-control" id="recipient-name" >  
                        <c:forEach items="${listadoSucursales}" var="s">                                            
                            <option value="${s.id_sucursal}" <c:if test="${s.id_sucursal == deposito.id_sucursal}"> selected </c:if>> ${s.nombre} </option>                  
                        </c:forEach>             
                    </select>                                   
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <a href="Depositos" class="btn btn-primary">Volver al listado</a>
                <button type="submit" class="btn btn-success">${accion} Depósito</button>  
            </div>
        </form>
    </body>
</html>