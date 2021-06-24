<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Usuario" %>
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
                    <c:forEach begin="0" end="${cantidad}"  items="${listadoClientes}" var="c">
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
                            <td><a href="Clientes?modo=AM&id_cliente=${c.id_cliente}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><c:if test="${rol != 4}"><a href="Clientes?modo=eliminar&a=a&id=${c.id_cliente}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></c:if></td>
                            </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>