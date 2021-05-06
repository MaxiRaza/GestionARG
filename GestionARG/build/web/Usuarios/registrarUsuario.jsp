<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <link href="CSS/misEstilos.css" rel="stylesheet" type="text/css"/>
        <title>GestionARG - Registrarse</title>
    </head>
    <body>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/><br><br>
        <h1 class="row justify-content-md-center">Registrar Usuario</h1>
        <div class="col-sm-3 container-sm"> <br>
            <form method="Post" action="Usuarios">
                <input type="hidden" name="txtIdUsuario" value="0" />
                <input type="hidden" name="txtIdContacto" value="0" />
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                    <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Apellido (*)</label>
                    <input type="text" name="txtApellido" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label class="col-form-label">Documento (*)</label>
                    <input type="number" name="txtDocumento" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label class="col-form-label">Fecha de Nacimiento</label>
                    <input type="date" name="txtFechaNac" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Dirección</label>
                    <input type="text" name="txtDireccion" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Correo (*)</label>
                    <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Telefono</label>
                    <input type="number" name="txtTelefono" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Usuario (*)</label>
                    <input type="text" name="txtAlias" required="required" class="form-control" id="recipient-name" >
                </div>
                <div class="form-group campo">
                    <label for="recipient-name" class="col-form-label">Contraseña (*)</label>
                    <input type="password" id="password" name="txtContrasenia" required="required" class="form-control" id="recipient-name" >
                    <span>Mostrar</span>
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Rol (*)</label>
                    <select name="cmbRoles" required="required" class="form-control" id="recipient-name" >
                        <c:forEach items="${listadoRoles}" var="r">
                            <option value="${r.id_rol}">${r.nombre}</option>
                        </c:forEach>             
                    </select>
                        <div><br>
                        <a href="Loginn" class="btn bg-primary">Volver</a>
                        <button type="submit" class="btn btn-success moverI">Registrar Usuario</button>             
                    </div>    
            </form>       
        </div>
        <script  type="text/javascript" src="JS/Funcionalidad.js"></script>
    </body>
</html>
