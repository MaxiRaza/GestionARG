<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="proveedor" class="Modelo.DTO.DTO_Proveedor" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Proveedor</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Proveedor</h1><br>
        <form method="Post" action="Proveedores">
            <div class="container">
                <div class="row">
                    <div class="col col-md-6 ">
                        <input type="hidden" name="txtIdProveedor" value="<jsp:getProperty name="proveedor" property="id_proveedor"></jsp:getProperty>" />
                        <input type="hidden" name="txtIdContacto" value="<jsp:getProperty name="proveedor" property="id_contacto"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="proveedor" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>                       
                                <div class="form-group">
                                    <label class="col-form-label">Cuit (*)</label>
                                    <input type="number" name="txtCuit" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="proveedor" property="cuit"></jsp:getProperty>" </c:if>>
                                </div>                        
                                <div class="form-group">
                                    <label class="col-form-label">Dirección </label>
                                    <input type="text" name="txtDireccion" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="proveedor" property="direccion"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Correo (*)</label>
                                    <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="proveedor" property="correo"></jsp:getProperty>" </c:if>>
                                </div>
                            </div>
                            <div class="col col-md-4">
                                <div class="form-group">
                                    <label class="col-form-label">Teléfono (*)</label>
                                    <input type="number" name="txtTelefono" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="proveedor" property="telefono"></jsp:getProperty>" </c:if>>
                                </div>                                          
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Tipo (*)</label>
                                    <select name="cmbTipos" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoTipos}" var="t">
                                    <option value="${t.id_tipo_proveedor}" <c:if test="${t.id_tipo_proveedor == proveedor.id_tipo_proveedor}"> selected </c:if> > ${t.nombre} </option>
                                </c:forEach>             
                            </select>                                   
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Clasificación (*)</label>
                            <select name="cmbClasificaciones" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoClasificaciones}" var="c">
                                    <option value="${c.id_clasificacion}" <c:if test="${c.id_clasificacion == proveedor.id_clasificacion}"> selected </c:if> > ${c.nombre} </option>
                                </c:forEach>             
                            </select>
                        </div>  
                    </div>
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <a href="Proveedores" class="btn btn-primary">Volver al listado</a>
                <button type="submit" class="btn btn-success">${accion} Proveedor</button>  
            </div>
        </form>
    </body>
</html>