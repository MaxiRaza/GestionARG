<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Proveedor" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <jsp:include page="../Componentes\formato.jsp"/>
    </head>
    <body>  

        <form method="Post" action="Productos">
            <div class="container ">
                <div class="row m-6 justify-content-md-center">
                    <div class="col-sm-1">
                        <label for="recipient-name" class="col-form-label">Categoria</label>
                    </div>
                    <div class="col-md-3">                               
                        <select name="cmbCategorias" required="required" class="form-control" id="recipient-name" >
                            <option value="0"> Seleccionar </option>    
                            <c:forEach items="${listadoCategorias}" var="c">                                            
                                <option value="${c.id_categoria}" <c:if test="${c.id_categoria == id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                            </c:forEach>             
                        </select>
                    </div>                                                
                    <div class="col-sm-1">
                        <label for="recipient-name" class="col-form-label">Marca</label>
                    </div>
                    <div class="col-md-3">
                        <select name="cmbMarcas" required="required" class="form-control" id="recipient-name" >
                            <option value="0"> Seleccionar </option>    
                            <c:forEach items="${listadoMarcas}" var="m">                                   
                                <option value="${m.id_marca}" <c:if test="${m.id_marca == id_marca}"> selected </c:if> > ${m.nombre} </option>
                            </c:forEach>             
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success" style="width: 120px"><i class="bi bi-search" style="size: 20px"></i></button>  
                    </div>
                    <div class="col-sm-2">
                        <a href="Productos?modo=Limpiar" class="btn btn-info" style="width: 120px">Limpiar</a>
                    </div>  
                </div> 
            </div>
        </form><br><br>

        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Código</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Fecha Elaboración</th>
                        <th scope="col">Fecha Vencimiento</th>
                        <th scope="col">Precio </th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Stock</th>
                        <th scope="col">Marca</th>
                        <th scope="col">Categoria</th>                        
                        <th scope="col">Depósito</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${cantidad}" items="${listadoProductos}" var="p">           
                        <tr>
                            <td> ${p.id_producto}  </td>
                            <td> ${p.codigo}  </td>
                            <td> ${p.nombre}  </td>
                            <td> ${p.fecha_fab}  </td>
                            <td> ${p.fecha_ven}  </td>
                            <td> $ ${p.precio}  </td>
                            <td> ${p.descripcion}  </td>
                            <td> ${p.stock}  </td>                        
                            <td> ${p.marca}  </td>
                            <td> ${p.categoria}  </td>
                            <td> ${p.deposito}  </td>
                            <td><a href="Productos?modo=AM&id_producto=${p.id_producto}" class="btn btn-warning" style="width: 70px"><i class="bi bi-pencil" style="font-size: 18px"></i></a></td>
                            <td><c:if test="${rol != 4}"><a href="Productos?modo=eliminar&a=a&id=${p.id_producto}" class="btn btn-danger" style="width: 70px"><i class="bi bi-trash" style="font-size:  18px"></i></a></c:if></td>
                            </tr> 
                    </c:forEach>                              
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>