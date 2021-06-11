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
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado Encargos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Encargos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Encargos?modo=AM" class="btn btn-info">Registrar Encargo</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <c:forEach begin="0" end="${cantidad}"  items="${listadoEncargos}" var="e">
                    <thead>            
                        <tr class="table-primary">
                            <th scope="col" style="display: "> # </th>
                            <th scope="col">Fecha </th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>                                       
                        <tr>
                            <td> ${e.id_encargo}  </td>
                            <td> ${e.fecha}  </td>  
                            <td> # </td>
                            <td> Proveedor </td>
                            <td> Producto  </td>
                            <td> Cantidad  </td>
                            <td><a href="Encargos?modo=AM&id_encargo=${e.id_encargo}" class="btn btn-success">Agregar Detalle</a></td>
                            <td><a href="Encargos?modo=eliminar&a=a&id_e=${e.id_encargo}" class="btn btn-danger">Eliminar Encargo</a></td>
                        </tr>                   
                        <c:forEach items="${listadoDetalles}" var="d">
                            <c:if test="${ e.id_encargo == d.id_encargo}" >
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td>${d.id_detalle_encargo}</td>                               
                                    <td> ${d.proveedor}  </td>
                                    <td> ${d.producto}  </td>
                                    <td> ${d.cantidad}  </td>
                                    <td><a href="Encargos?modo=AM&id_detalle_encargo=${d.id_detalle_encargo}" class="btn btn-warning"> Editar Detalle</a></td>
                                    <td><a href="Encargos?modo=eliminar&a=a&id=${d.id_detalle_encargo}" class="btn btn-danger">Eliminar Detalle</a></td>                               
                                </tr>   
                            </c:if>                       
                        </c:forEach>              
                    </c:forEach> 
                    </div>      
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>