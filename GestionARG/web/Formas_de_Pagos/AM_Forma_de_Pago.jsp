<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="forma_de_pago" class="Modelo.Forma_de_Pago" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Forma de Pago</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Forma de Pago</h1><br>
        <form method="Post" action="Formas_de_Pagos">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdFormaDePago" value="<jsp:getProperty name="forma_de_pago" property="id_forma_de_pago"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="forma_de_pago" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>                                                  
                    </div><br>
                    <div class="row justify-content-md-center" >
                        <a href="Formas_de_Pagos" class="btn btn-primary">Volver al listado</a>
                        <button type="submit" class="btn btn-success">${accion} Forma de Pago</button>  
            </div>
        </form>
    </body>
</html>