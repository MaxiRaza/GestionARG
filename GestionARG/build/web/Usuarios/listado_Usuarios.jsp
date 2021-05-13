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
        <title>GestionARG - Listado Usuarios</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Usuarios</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Usuarios?modo=AM" class="btn btn-info">Registrar Usuario</a><br><br><br>
        </div>
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
                            <td><a href="Usuarios?modo=AM&id_usuario=${u.id_usuario}" class="btn btn-warning">Editar</a></td>
                            <td><a class="btn btn-danger" data-toggle="modal" data-target="#confirmacion">Eliminar</a>
                                <div class="modal fade" id="confirmacion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                ¿ Seguro que desea eliminar al usuario ${u.alias} ?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <a href="Usuarios?modo=eliminar&id_usuario=${u.id_usuario}" class="btn btn-danger" >Eliminar</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
    </body>
</html>