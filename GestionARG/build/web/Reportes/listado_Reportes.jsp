<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Reportes</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Reportes</h1><br>                 
        <div class="row justify-content-md-center"><br><br> <br> 
            <h4>Top diéz Productos en  ventas</h4>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Categoría</th>
                        <th scope="col">Cantidad de ventas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoTopProductos}" var="v">
                        <tr>
                            <td> ${v.id_producto}  </td>
                            <td> ${v.nombre}  </td>
                            <td> ${v.marca}  </td>
                            <td> ${v.categoria}  </td>
                            <td> ${v.stock}  </td>
                        </tr>    
                    </c:forEach>                
                </tbody>
            </table>
            <center><a href="Reportess?modo=barra"  class="btn btn-primary">Mostrar gráfico <i class="bi bi-bar-chart-line"></i></a></center>
        </div>
        <br> <div class="row justify-content-md-center"><br><br> <br> 
            <h4>Top cinco Marcas mas vendidas</h4>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">Marca</th>
                        <th scope="col">Cantidad de ventas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoTopMarcas}" var="v">
                        <tr>
                            <td> ${v.nombre}  </td>
                            <td> ${v.id_categoria}  </td>
                        </tr>    
                    </c:forEach>                
                </tbody>
            </table>
            <center><a href="Reportess?modo=torta"  class="btn btn-primary ">Ver gráfico <i class="bi bi-pie-chart-fill"></i></a></center>          
        </div>
        <br> <div class="row justify-content-md-center"><br><br> <br> 
            <h4>Facturación mensual (2021)</h4>
        </div>
        <div class="container-fluid">
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">Mes</th>
                        <th scope="col">Importe</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listadoGananciaMensual}" var="v">
                        <tr>
                            <td> ${v.id_factura}  </td>
                            <td>$ ${v.descuento}  </td>
                        </tr>    
                    </c:forEach>                
                </tbody>
            </table><center><a href="Reportess?modo=lineal"  class="btn btn-primary">Mostrar gráfico <i class="bi bi-graph-up"></i></a></center>            
        </div>
    </body>
</html>