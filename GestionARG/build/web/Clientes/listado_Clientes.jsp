<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado Clientes</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Clientes</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Clientes?modo=AM" class="btn btn-info">Registrar Cliente</a><br><br><br>
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Documento</th>
                        <th scope="col">Fecha de Nacimiento</th>
                        <th scope="col">Dirección</th>
                        <th scope="col">Correo</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Tipo Cliente</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoClientes}" var="c">
                        <tr>
                            <td> ${c.id_cliente}  </td>
                            <td> ${c.nombre}  </td>
                            <td> ${c.apellido}   </td>
                            <td> ${c.documento}  </td>
                            <td> ${c.fecha_nac}  </td>
                            <td> ${c.direccion}  </td>
                            <td> ${c.correo}  </td>
                            <td> ${c.telefono}  </td>
                            <td> ${c.tipo_cliente}  </td>
                            <td><a href="Clientes?modo=AM&id_cliente=${c.id_cliente}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Clientes?modo=eliminar&a=a&id=${c.id_cliente}" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
    </body>
</html>