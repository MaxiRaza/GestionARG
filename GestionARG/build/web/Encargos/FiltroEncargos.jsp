<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="encargo" class="Modelo.DTO.DTO_Encargo" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Encargo</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Encargo</h1><br>
        <form method="Post" action="Encargos">
            <div class="container ">
                <div class="row justify-content-md-center">
                    <div class="col col-md-4 ">
                        <input type="hidden" name="txtIdEncargo" value="<jsp:getProperty name="encargo" property="id_encargo"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Categoria (*)</label>
                                <select name="cmbCategorias" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoCategorias}" var="c">
                                    <option value="${c.id_categoria}" <c:if test="${c.id_categoria == encargo.id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                                </c:forEach>             
                            </select>
                        </div>
                    </div>
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <a href="Encargos" class="btn btn-primary">Volver al listado</a>
                <button type="submit" class="btn btn-success">Siguiente</button>  
            </div>
        </form>
    </body>
</html>