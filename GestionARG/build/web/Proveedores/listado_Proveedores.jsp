<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Proveedor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
    </head>
    <body>             
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
                    <c:forEach begin="0" end="${cantidad}"  items="${listadoProveedores}" var="p">
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
                            <td><a href="Proveedores?modo=AM&id_proveedor=${p.id_proveedor}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><c:if test="${rol != 4}"><a href="Proveedores?modo=eliminar&a=a&id=${p.id_proveedor}"class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a> </c:if></td>                                 
                            </tr>
                    </c:forEach> 
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>