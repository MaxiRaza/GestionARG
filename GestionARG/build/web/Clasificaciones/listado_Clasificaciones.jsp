<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Clasificacion" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado de Clasificaciónes</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Clasificaciones</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Clasificaciones?modo=AM" class="btn btn-info">Registrar Clasificación</a><br><br><br> 
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
                    <c:forEach items="${listadoClasificaciones}" var="c">
                        <tr>
                            <td> ${c.id_clasificacion}  </td>
                            <td> ${c.nombre}  </td>
                            <td><a href="Clasificaciones?modo=AM&id_clasificacion=${c.id_clasificacion}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Clasificaciones?modo=eliminar&a=a&id=${c.id_clasificacion}"class="btn btn-danger" >Eliminar</a></td>                                                                                                 
                        </tr> 
                    </c:forEach>                    
                </tbody>
            </table>   
        </div>
    </body>
</html>