<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Factura" %>
<%@page import= "Modelo.Detalle_Factura" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <title>GestionARG - Listado Facturas</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Facturas</h1><br>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <c:forEach items="${listadoFacturas}" var="f">
                    <thead class="table-primary">
                        <tr>
                            <th>Factura</th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th></th>                       
                        </tr>
                    </thead>
                    <thead
                        <tr>
                            <th scope="col"> # </th>
                            <th scope="col">Fecha </th>
                            <th scope="col">Sucursal </th>
                            <th scope="col">Cliente </th>
                            <th scope="col">Vendedor </th>
                            <th scope="col">Forma de Pago </th>                    
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>                                         
                        <tr>
                            <c:set var="id" scope = "session" value="${f.id_factura}"/>
                            <td> ${f.id_factura}  </td>
                            <td> ${f.fecha}  </td>                           
                            <td> ${f.sucursal} </td>
                            <td> ${f.cliente}  </td>
                            <td> ${f.usuario}  </td>
                            <td> ${f.forma_de_pago} </td>
                            <td></td>
                        </tr>
                    <thead bgcolor="#002D3B">
                    <th></th>
                    <th>Detalles</th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>
                    <th></th>   
                    </thead>
                    <thead>
                        <tr>
                            <th></th>
                            <th></th>
                            <th scope="col">Producto </th>
                            <th scope="col">Marca </th>
                            <th scope="col">Importe </th>
                            <th scope="col">Cantidad </th>
                            <th scope="col"> Sub Total </th>
                        </tr>
                    </thead>
                    <c:forEach items="${listadoDetalles}" var="d">
                        <c:if test="${ id == d.id_factura}" >
                            <tr>
                                <td></td>
                                <td></td>    
                                <td> ${d.producto}  </td>
                                <td> ${d.marca}  </td>
                                <td> $ ${d.importe}  </td>
                                <td> ${d.cantidad} /u  </td>
                                <td> $ ${d.importe * d.cantidad} </td>  
                            </tr>   
                        </c:if>                       
                    </c:forEach>   
                </c:forEach> 
        </div>      
    </tbody>
</table>   
</div>
</body>
</html>