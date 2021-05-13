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
        <title>GestionARG - Listado Clientes</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Clientes</h1><br>           
        <a href="Clientes?modo=AM" class="btn btn-info float-right">Registrar Cliente</a><br><br><br>             
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
                        <th scope="col">Tipo Cliente</th>
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
                            <td><a class="btn btn-danger" data-toggle="modal" data-target="#confirmacion">Eliminar</a>
                                <div class="modal fade" id="confirmacion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                ¿ Seguro que desea eliminar al usuario ${c.nombre} ${c.apellido}?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                <a href="Clientes?modo=eliminar&id_cliente=${c.id_cliente}" class="btn btn-danger" >Eliminar</a>
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