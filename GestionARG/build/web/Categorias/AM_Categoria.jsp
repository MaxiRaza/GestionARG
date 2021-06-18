<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>      
        <jsp:useBean id="categoria" class="Modelo.Categoria" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Categoría</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion } Categoría</h1><br>
        <form method="Post" action="Categorias">
            <div class="container col-md-3">                  
                <input type="hidden" name="txtIdCategoria" value="<jsp:getProperty name="categoria" property="id_categoria"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="categoria" property="nombre"></jsp:getProperty>" </c:if>>
                        </div>                                                  
                    </div><br>
                    <div class="row justify-content-md-center" >
                        <a href="Categorias" class="btn btn-primary">Volver al listado</a>
                        <button type="submit" class="btn btn-success">${accion} Categoría</button>  
            </div>
        </form>
    </body>
</html>