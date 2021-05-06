<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado Usuarios</title>     
    </head>
    <body>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
            <br><h1  class="row justify-content-md-center">Listado de Usuarios</h1><br>           
            <a href="Usuarios?modo=registrar" class="btn btn-info float-right">Registrar Usuario</a><br><br><br>             
            <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead> 
                    <tr>
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoUsuarios}" var="u">
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
                            <td><a href="Usuarios?modo=modificar&id_usuario=${u.id_usuario}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Usuarios?modo=eliminar&id_usuario=${u.id_usuario}"  class="btn btn-danger">Eliminar</a></td>
                        </tr>    
                    </c:forEach>                
                </tbody>
            </table>   
        </div>
    </body>
</html>