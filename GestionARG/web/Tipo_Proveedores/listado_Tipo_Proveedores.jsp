<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Tipo_Proveedor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado de  tipos de Proveedores</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de tipos de Proveedores</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Tipo_Proveedores?modo=AM" class="btn btn-info">Registrar tipo de Proveedor</a><br><br><br> 
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
                    <c:forEach begin="0" end="${cantidad}"  items="${listadoTiposProveedores}" var="t">
                        <tr>
                            <td> ${t.id_tipo_proveedor}  </td>
                            <td> ${t.nombre}  </td>
                            <td><a href="Tipo_Proveedores?modo=AM&id_tipo_proveedor=${t.id_tipo_proveedor}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Tipo_Proveedores?modo=eliminar&a=a&id=${t.id_tipo_proveedor}" class="btn btn-danger">Eliminar</a></td>                                                                                                 
                        </tr> 
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>