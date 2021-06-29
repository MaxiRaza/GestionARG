<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Encargo" %>
<%@page import= "Modelo.Detalle_Encargo" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
    </head>
    <body>             
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <c:forEach begin="0" end="${cantidad}"  items="${listadoEncargos}" var="e">
                    <thead>            
                        <tr class="table-primary">
                            <th> # </th>
                            <th> Fecha </th>
                            <th> Estado </th>
                            <th></th>
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
                            <td> ${e.estado} </td>
                            <td> # </td>
                            <td> Proveedor </td>
                            <td> Producto  </td>
                            <td> Cantidad  </td>
                            <td> Importe  </td>
                            <td><a href="Encargos?modo=AM&id_encargo=${e.id_encargo}" class="btn btn-success" style="width: 70px"><i class="bi bi-plus-lg" style="font-size: 18px"></i></a></td>
                            <td><c:if test="${rol != 4}"><a href="Encargos?modo=eliminar&a=a&id_e=${e.id_encargo}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></c:if></td>
                            </tr>                   
                        <c:forEach items="${listadoDetalles}" var="d">
                            <c:if test="${ e.id_encargo == d.id_encargo}" >
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>${d.id_detalle_encargo}</td>                               
                                    <td> ${d.proveedor}  </td>
                                    <td> ${d.producto}  </td>
                                    <td> ${d.cantidad}  </td>
                                    <td> $ ${d.cantidad * d.importe}  </td>
                                    <td><a href="Encargos?modo=AM&id_detalle_encargo=${d.id_detalle_encargo}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                                    <td><c:if test="${rol != 4}"><a href="Encargos?modo=eliminar&a=a&id=${d.id_detalle_encargo}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size: 18px"></i></a></c:if></td>                               
                                </tr>   
                                        </c:if>                       
                                    </c:forEach>  
                        <tr></tr>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                    <td class="table-dark">Total</td>
                    <td class="table-dark">$ ${e.cantidad}</td>
                    <td class="table-dark"></td>
                    <td class="table-dark"></td>
                </c:forEach> 
        </div>      
    </tbody>
</table>   
</div>
<jsp:include page="../Componentes\limiteLista.jsp"/>
</body>
</html>