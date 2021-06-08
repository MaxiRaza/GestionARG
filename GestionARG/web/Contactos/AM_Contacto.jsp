<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="contacto" class="Modelo.Contacto" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Contacto</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Contacto</h1><br>
        <form method="Post" action="Contactos">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdContacto" value="<jsp:getProperty name="contacto" property="id_contacto"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Teléfono (*)</label>
                        <input type="text" name="txtTelefono" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="contacto" property="telefono"></jsp:getProperty>" </c:if>>
                        </div>       
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Correo (*)</label>
                            <input type="text" name="txtCorreo" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="contacto" property="correo"></jsp:getProperty>" </c:if>>
                        </div>
                    </div><br>
                    <div class="row justify-content-md-center" >
                        <a href="Contactos" class="btn btn-primary">Volver al listado</a>
                        <button type="submit" class="btn btn-success">${accion} Contacto</button>  
            </div>
        </form>
    </body>
</html>