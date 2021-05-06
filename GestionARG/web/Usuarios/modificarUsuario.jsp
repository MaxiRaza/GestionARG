<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Editar Usuario</title>
    </head>
    <body>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <br><h1 class="row justify-content-md-center">Editar Usuario</h1><br>       
        <jsp:useBean id="usuario" class="Modelo.DTO.DTO_Usuario" scope="request"></jsp:useBean>
            <div class="col-sm-3 container-sm">
                <form method="Post" action="Usuarios">
                    <input type="hidden" name="txtIdUsuario" value="<jsp:getProperty name="usuario" property="id_usuario"></jsp:getProperty>" />
                <input type="hidden" name="txtIdContacto" value="<jsp:getProperty name="usuario" property="id_contacto"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="nombre"></jsp:getProperty>" >
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Apellido (*)</label>
                        <input type="text" name="txtApellido" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="apellido"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Documento (*)</label>
                        <input type="number" name="txtDocumento" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="documento"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Fecha de Nacimiento </label>
                        <input type="date" name="txtFechaNac" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="fecha_nac"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Dirección </label>
                        <input type="text" name="txtDireccion" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="direccion"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Correo (*)</label>
                        <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="correo"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Teléfono </label>
                        <input type="number" name="txtTelefono" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="telefono"></jsp:getProperty>">
                    </div>                   
                    <div class="form-group">
                        <label class="col-form-label">Usuario (*)</label>
                        <input type="text" name="txtAlias" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="alias"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label class="col-form-label">Contraseña (*)</label>
                        <input type="text" name="txtContrasenia" required="required" class="form-control" id="recipient-name" value="<jsp:getProperty name="usuario" property="contrasenia"></jsp:getProperty>">
                    </div>
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Rol (*)</label>
                        <select name="cmbRoles" required="required" class="form-control" id="recipient-name" >
                        <c:forEach items="${listadoRoles}" var="r">
                            <option value="${r.id_rol}" <c:if test="${r.id_rol == usuario.id_rol}"> selected </c:if> > ${r.nombre} </option>
                        </c:forEach>             
                    </select>                                   
                </div>
                <div>
                    <a href="Usuarios" class="btn btn-primary">Volver al listado</a>
                    <button type="submit" class="btn btn-success moverI">Editar Usuario</button>               
                </div>                   
            </form>       
        </div>
    </body>
</html>