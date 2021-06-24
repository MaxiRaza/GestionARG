<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="marca" class="Modelo.Marca" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Marca</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Marca</h1><br>
        <form method="Post" action="Marcas">
            <div class="container col-md-4">                  
                <input type="hidden" name="txtIdMarca" value="<jsp:getProperty name="marca" property="id_marca"></jsp:getProperty>" />
                    <div class="form-group">
                        <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                        <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="marca" property="nombre"></jsp:getProperty>" </c:if>>
                        </div>                                                  
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Categoria (*)</label>
                            <select name="cmbCategorias" required="required" class="form-control" id="recipient-name" >  
                        <c:forEach items="${listadoCategorias}" var="c">                                            
                            <option value="${c.id_categoria}" <c:if test="${c.id_categoria == marca.id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                        </c:forEach>             
                    </select>                                   
                </div><br>
                <jsp:include page="../Componentes\botonesAM.jsp"/>      
            </div>
        </form>
    </body>
</html>