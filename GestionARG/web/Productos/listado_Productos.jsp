<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Proveedor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado Productos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Productos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Productos?modo=AM" class="btn btn-info">Registrar Producto</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Fecha Elaboración</th>
                        <th scope="col">Fecha Vencimiento</th>
                        <th scope="col">Precio </th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Categoria</th>                        
                        <th scope="col">Depósito</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${cantidad}" items="${listadoProductos}" var="p">           
                        <tr>
                            <td> ${p.id_producto}  </td>
                            <td> ${p.codigo}  </td>
                            <td> ${p.nombre}  </td>
                            <td> ${p.fecha_fab}  </td>
                            <td> ${p.fecha_ven}  </td>
                            <td> ${p.precio}  </td>
                            <td> ${p.descripcion}  </td>
                            <td> ${p.stock}  </td>                        
                            <td> ${p.marca}  </td>
                            <td> ${p.categoria}  </td>
                            <td> ${p.deposito}  </td>
                            <td><a href="Productos?modo=AM&id_producto=${p.id_producto}" class="btn btn-warning">Editar</a></td>
                            <td><c:if test="${rol != 4}"><a href="Productos?modo=eliminar&a=a&id=${p.id_producto}" class="btn btn-danger">Eliminar</a></c:if></td>
                            </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>