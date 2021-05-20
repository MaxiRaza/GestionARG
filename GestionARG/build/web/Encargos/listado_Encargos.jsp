<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Encargo" %>
<%@page import= "Modelo.Detalle_Encargo" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <title>GestionARG - Listado Encargos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Encargos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Encargos?modo=AM" class="btn btn-info">Registrar Encargo</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr>
                        <th scope="col" style="display: "> # </th>
                        <th scope="col">Fecha </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoEncargos}" var="e">                      
                        <tr>
                            <c:set var="id" scope = "session" value="${e.id_encargo}"/>
                            <td style="display: "> ${e.id_encargo}  </td>
                            <td> ${e.fecha}  </td>                           
                            <td> Proveedor </td>
                            <td> Producto  </td>
                            <td> Cantidad  </td>
                            <td style="display: none"> </td>
                            <td><a href="Encargos?modo=AM&id_encargo=${e.id_encargo}" class="btn btn-success">Agregar</a></td>
                            <td><a class="btn btn-danger" data-toggle="modal" data-target="#confirmacionE">Eliminar Encargo</a></td>
                             <div class="modal fade" id="confirmacionE" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ¿ Seguro que desea eliminar el encargo con sus detalles ${id} ${e.id_encargo}?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                <a href="Encargos?modo=eliminar&id_encargo=${id}" class="btn btn-danger" >Eliminar</a>
                            </div>
                        </div>
                    </div>
                </div>
                        </tr>                   
                        <c:forEach items="${listadoDetalles}" var="d">
                            <c:if test="${ id == d.id_encargo}" >
                                <tr>
                                    <c:set var="idde" scope = "session" value="${d.id_detalle_encargo}"/>
                                    <c:set var="c" scope = "session" value="${d.cantidad}"/>
                                    <td style="display: "> </td>
                                    <td></td>                               
                                    <td> ${d.proveedor}  </td>
                                    <td> ${d.producto}  </td>
                                    <td> ${d.cantidad}  </td>
                                    <td style="display: none "> ${d.id_detalle_encargo} </td>
                                    <td><a href="Encargos?modo=AM&id_detalle_encargo=${d.id_detalle_encargo}" class="btn btn-warning"> Editar </a></td>
                                    <td><a class="btn btn-danger" data-toggle="modal" data-target="#confirmacionD">Eliminar Detalle</a></td>                               
                                </tr>   
                            </c:if>                       
                        </c:forEach>              
                    </c:forEach> 

               

                <div class="modal fade" id="confirmacionD" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                                <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                ¿ Seguro que desea eliminar este detalle ${c}?
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                <a href="Encargos?modo=eliminar&id_detalle_encargo=${idde}" class="btn btn-danger" >Eliminar</a>
                            </div>
                        </div>
                    </div>
                </div>

                </tbody>
            </table>   
        </div>
    </body>
</html>