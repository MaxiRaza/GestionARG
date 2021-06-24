<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="producto" class="Modelo.DTO.DTO_Producto" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Producto</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Producto</h1><br>
        <form method="Post" action="Productos">
            <div class="container ">
                <div class="row justify-content-md-center">
                    <div class="col col-md-4 ">
                        <input type="hidden" name="txtIdProducto" value="<jsp:getProperty name="producto" property="id_producto"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Categoria (*)</label>
                                <select name="cmbCategoriass" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoCategorias}" var="c">
                                    <option value="${c.id_categoria}" <c:if test="${c.id_categoria == producto.id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                                </c:forEach>             
                            </select>
                        </div>
                    </div>
                </div><br>
                <div class="row justify-content-md-center" >
                    <div class="col-sm-2">
                        <a href="Productos" class="btn btn-primary" style="width: 100px"><i class="bi bi-reply-fill" style="font-size: 20px"></i></a>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success" style="width: 100px"><i class="bi bi-arrow-right" style="font-size: 20px"></i></button>  
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>