<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Proveedor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado Proveedores</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Proveedores</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Proveedores?modo=AM" class="btn btn-info">Registrar Proveedor</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Cuit</th>
                        <th scope="col">Dirección</th>
                        <th scope="col">Correo</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Tipo Proveedor</th>
                        <th scope="col">Clasificación</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Categoría</th>        
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoProveedores}" var="p">
                        <tr>
                            <td> ${p.id_proveedor}  </td>
                            <td> ${p.nombre}  </td>
                            <td> ${p.cuit}  </td>
                            <td> ${p.direccion}  </td>
                            <td> ${p.correo}  </td>
                            <td> ${p.telefono}  </td>
                            <td> ${p.tipo}  </td>
                            <td> ${p.clasificacion}  </td>
                            <td> ${p.marca}  </td>                            
                            <td> ${p.categoria}  </td>
                            <td><a href="Proveedores?modo=AM&id_proveedor=${p.id_proveedor}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Proveedores?modo=eliminar&a=a&id=${p.id_proveedor}"class="btn btn-danger">Eliminar</a> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
    </body>
</html>