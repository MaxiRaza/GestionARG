<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Tipo_Cliente" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado de Roles</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Roles</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Roles?modo=AM" class="btn btn-info">Registrar Rol</a><br><br><br> 
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
                    <c:forEach begin="0" end="${cantidad}" items="${listadoRoles}" var="r">
                        <tr>
                            <td> ${r.id_rol}  </td>
                            <td> ${r.nombre}  </td>
                            <td><a href="Roles?modo=AM&id_rol=${r.id_rol}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Roles?modo=eliminar&a=a&id=${r.id_rol}" class="btn btn-danger">Eliminar</a></td>  
                        </tr> 
                    </c:forEach> 
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>