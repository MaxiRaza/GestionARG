<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Forma_de_Pago" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Listado de Formas de Pagos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Formas de Pagos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Formas_de_Pagos?modo=AM" class="btn btn-info" style="width: 100px"><i class="bi bi-plus-lg" style="font-size: 18px"></i></a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${cantidad}" items="${listadoFormasDePago}" var="r">
                        <tr>
                            <td> ${r.id_forma_de_pago}  </td>
                            <td> ${r.nombre}  </td>
                            <td><a href="Formas_de_Pagos?modo=AM&id_forma_de_pago=${r.id_forma_de_pago}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><a href="Formas_de_Pagos?modo=eliminar&a=a&id=${r.id_forma_de_pago}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></td>                                                                                                 
                        </tr> 
                </c:forEach>                    
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>