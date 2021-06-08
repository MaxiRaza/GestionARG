<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Forma_de_Pago" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <title>GestionARG - Listado de Formas de Pagos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Formas de Pagos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Formas_de_Pagos?modo=AM" class="btn btn-info">Registrar Forma de Pago</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoFormasDePago}" var="r">
                        <tr>
                            <td> ${r.id_forma_de_pago}  </td>
                            <td> ${r.nombre}  </td>
                            <td><a href="Formas_de_Pagos?modo=AM&id_forma_de_pago=${r.id_forma_de_pago}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Formas_de_Pagos?modo=eliminar&id_forma_de_pago=${r.id_forma_de_pago}" class="btn btn-danger" data-toggle="modal" data-target="#confirmacion" >Eliminar</a></td>                                                                                                 
                        </tr> 
                    <div class="modal fade" id="confirmacion" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Confirmar</h5>
                                    <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    ¿ Seguro que desea eliminar ?
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    <a href="Formas_de_Pagos?modo=eliminar&b=1" class= "btn btn-danger" >Eliminar</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
    </body>
</html>