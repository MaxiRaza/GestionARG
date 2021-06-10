<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Deposito" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado de Depósitos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Depósitos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Depositos?modo=AM" class="btn btn-info">Registrar Depósito</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Sucursal</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoDepositos}" var="d">
                        <tr>
                            <td> ${d.id_deposito}  </td>
                            <td> ${d.deposito}  </td>
                            <td> ${d.sucursal}  </td>
                            <td><a href="Depositos?modo=AM&id_deposito=${d.id_deposito}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Depositos?modo=eliminar&a=a&id=${d.id_deposito}" class="btn btn-danger">Eliminar</a></td>                                                                                                 
                        </tr> 
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
    </body>
</html>