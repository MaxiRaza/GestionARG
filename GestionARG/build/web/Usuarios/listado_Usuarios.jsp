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
                        <th scope="col">Usuario</th>
                        <th scope="col">Contraseña</th>
                        <th scope="col">Rol</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${cantidad}" items="${listadoUsuarios}" var="u">
                        <tr>
                            <td> ${u.id_usuario}  </td>
                            <td> ${u.nombre}  </td>
                            <td> ${u.apellido}   </td>
                            <td> ${u.documento}  </td>
                            <td> ${u.fecha_nac}  </td>
                            <td> ${u.direccion}  </td>
                            <td> ${u.correo}  </td>
                            <td> ${u.telefono}  </td>
                            <td> ${u.alias}  </td>
                            <td> ${u.contrasenia}  </td>
                            <td> ${u.rol}  </td>
                            <td><a href="Usuarios?modo=AM&id_usuario=${u.id_usuario}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><a href="Usuarios?modo=eliminar&a=a&id=${u.id_usuario}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></td>
                        </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>