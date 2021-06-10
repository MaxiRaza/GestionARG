<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Categoria" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado de Categoría</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Categorías</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Categorias?modo=AM" class="btn btn-info">Registrar Categoría</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoCategorias}" var="c">
                        <tr>
                            <td> ${c.id_categoria}  </td>
                            <td> ${c.nombre}  </td>
                            <td><a href="Categorias?modo=AM&id_categoria=${c.id_categoria}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Categorias?modo=eliminar&a=a&id=${c.id_categoria}" class="btn btn-danger" >Eliminar</a></td>                                                                                                 
                        </tr> 
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
    </body>
</html>