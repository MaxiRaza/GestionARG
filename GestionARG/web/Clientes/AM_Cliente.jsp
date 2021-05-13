<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="cliente" class="Modelo.DTO.DTO_Cliente" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Cliente</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Cliente</h1><br>
        <form method="Post" action="Clientes">        
            <div class="container">
                <div class="row">
                    <div class="col col-md-6 ">
                        <input type="hidden" name="txtIdCliente" value="<jsp:getProperty name="cliente" property="id_cliente"></jsp:getProperty>" />
                        <input type="hidden" name="txtIdContacto" value="<jsp:getProperty name="cliente" property="id_contacto"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Apellido (*)</label>
                                    <input type="text" name="txtApellido" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="apellido"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Documento (*)</label>
                                    <input type="number" name="txtDocumento" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="documento"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Fecha de Nacimiento </label>
                                    <input type="date" name="txtFechaNac" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="fecha_nac"></jsp:getProperty>" </c:if>>
                                </div>
                            </div>
                            <div class="col col-md-6 ">
                                <div class="form-group">
                                    <label class="col-form-label">Dirección </label>
                                    <input type="text" name="txtDireccion" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="direccion"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Correo (*)</label>
                                    <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="correo"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Teléfono (*)</label>
                                    <input type="number" name="txtTelefono" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="cliente" property="telefono"></jsp:getProperty>" </c:if>>
                                </div>                                          
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Tipo (*)</label>
                                    <select name="cmbTipos" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoTipos}" var="t">
                                    <option value="${t.id_tipo_cliente}" <c:if test="${t.id_tipo_cliente == cliente.id_tipo_cliente}"> selected </c:if> > ${t.nombre} </option>
                                </c:forEach>             
                            </select>                                   
                        </div>
                    </div>     
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <a href="Clientes" class="btn btn-primary">Volver al listado</a>
                <button type="submit" class="btn btn-success">${accion} Cliente</button>  
            </div>                   
        </form>       
    </body>
</html>