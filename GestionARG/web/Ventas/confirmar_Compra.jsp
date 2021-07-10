<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>        
        <jsp:include page="../Componentes\formato.jsp"/>
    </head>
    <body>
        <br><h1 class="row justify-content-md-center">Confimar Compra</h1><br>
        <form method="Post" action="Compras">
            <div class="container col-md-3">                                                              
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Sucrusal (*)</label>
                    <select name="cmbSucursales" required="required" class="form-control" id="recipient-name" >  
                        <c:forEach items="${listadoSucursales}" var="s">                                            
                            <option value="${s.id_sucursal}"> ${s.nombre} </option>                  
                        </c:forEach>             
                    </select>                                   
                </div>
                <div class="form-group">
                    <label for="recipient-name" class="col-form-label">Forma de Pago (*)</label>
                    <select name="cmbFormasdePago" required="required" class="form-control" id="recipient-name" >  
                        <c:forEach items="${listadoFormasdePago}" var="f">                                            
                            <option value="${f.id_forma_de_pago}"> ${f.nombre} </option>                  
                        </c:forEach>             
                    </select>                                   
                </div><br>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Producto </th>
                            <th>Cantidad</th>
                            <th>Precio</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listadoCarrito}" var="c">
                            <tr>
                                <th>${c.id_compra}</th>
                                <td>${c.nombre}</td>
                                <td>
                                    <input style="width: 50px" type="number" name="txtCantidad${c.id_compra}" required="required" value="1" min="1" max="100">
                                </td>
                                <td>$ ${c.precio} /u</td>
                                <td><a href="Compras?modo=comprar&Bde=Bde&id_p=${c.id_producto}" class="btn btn-danger" style="width: 50px"><i class="bi bi-trash" style="font-size:  18px"></i></a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="row justify-content-md-center" >
                    <div class="col-md-4">
                        <a href="Compras" class="btn btn-primary" style="width: 100px"><i class="bi bi-reply-fill" style="font-size: 20px"></i></a>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" class="btn btn-success" style="width: 100px"><i class="bi bi-cash-coin" style="font-size: 20px"></i></button> 
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>