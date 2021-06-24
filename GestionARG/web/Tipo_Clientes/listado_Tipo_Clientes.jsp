<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Tipo_Cliente" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado de  tipos de Clientes</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de tipos de Clientes</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Tipo_Clientes?modo=AM" class="btn btn-info" style="width: 100px"><i class="bi bi-plus-lg" style="font-size: 18px"></i></a><br><br><br> 
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
                    <c:forEach begin="0" end="${cantidad}" items="${listadoTiposClientes}" var="t">
                        <tr>
                            <td> ${t.id_tipo_cliente}  </td>
                            <td> ${t.nombre}  </td>
                            <td><a href="Tipo_Clientes?modo=AM&id_tipo_cliente=${t.id_tipo_cliente}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><a href="Tipo_Clientes?modo=eliminar&a=a&id=${t.id_tipo_cliente}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></td>                                                                                                 
                        </tr> 
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>