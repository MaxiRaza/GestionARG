<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="usuario" class="Modelo.DTO.DTO_Usuario" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Usuario</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Usuario</h1><br>        
        <form method="Post" action="Usuarios">
            <div class="container">
                <div class="row">
                    <div class="col col-md-6 ">
                        <input type="hidden" name="txtIdUsuario" value="<jsp:getProperty name="usuario" property="id_usuario"></jsp:getProperty>" />
                        <input type="hidden" name="txtIdContacto" value="<jsp:getProperty name="usuario" property="id_contacto"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Apellido (*)</label>
                                    <input type="text" name="txtApellido" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="apellido"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Documento (*)</label>
                                    <input type="number" name="txtDocumento" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="documento"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Fecha de Nacimiento </label>
                                    <input type="date" name="txtFechaNac" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="fecha_nac"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Dirección </label>
                                    <input type="text" name="txtDireccion" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="direccion"></jsp:getProperty>" </c:if>>
                                </div>
                            </div>
                            <div class="col col-md-6 ">
                                <div class="form-group">
                                    <label class="col-form-label">Correo (*)</label>
                                    <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="correo"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Teléfono </label>
                                    <input type="number" name="txtTelefono" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="telefono"></jsp:getProperty>" </c:if>>
                                </div>                   
                                <div class="form-group">
                                    <label class="col-form-label">Usuario (*)</label>
                                    <input type="text" name="txtAlias" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="alias"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Contraseña (*)</label>
                                    <input type="text" name="txtContrasenia" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="usuario" property="contrasenia"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Rol (*)</label>
                                    <select name="cmbRoles" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoRoles}" var="r">
                                    <option value="${r.id_rol}" <c:if test="${r.id_rol == usuario.id_rol}"> selected </c:if> > ${r.nombre} </option>
                                </c:forEach>             
                            </select>                                   
                        </div>
                    </div>
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <c:if test="${!admin}"><a href="Loginn?modo=iniciarSesion" class="btn btn-primary">Volver</a></c:if>
                <c:if test="${admin}"><a href="Usuarios" class="btn btn-primary">Volver al listado</a></c:if>
                <button type="submit" class="btn btn-success">${accion} Usuario</button>  
            </div>                   
        </form>       
    </body>
</html>