<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:useBean id="producto" class="Modelo.DTO.DTO_Producto" scope="request"></jsp:useBean>
        </head>
        <body>
            <br><h1  class="row justify-content-md-center">Listado de Articulos</h1><br><br>

            <form method="Post" action="Facturas">
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
                    <div class="col-sm-1">
                        <button type="submit" class="btn btn-success">Filtrar</button>  
                    </div>
                    <div class="col-sm-1">
                        <a href="Facturas?modo=Limpiar" class="btn btn-info">Limpiar</a>
                    </div>
                </div>
            </div>         
        </form><br>

        <div class="container-fluid">    
            <div class="row m-4 justify-content-md-center">
                <c:forEach begin="0" end="${cantidad}" items="${listadoProductos}" var="p">           
                    <div class="col-12 col-sm-6 col-md-6 col-lg-3 col-xl-3">
                        <div class="card-deck mb-3 text-center">
                            <div class="card mb-4 shadow-sm">
                                <div class="card-header">
                                    <h4 class="my-0 font-weight-normal">${p.nombre}</h4>
                                </div>
                                <div class="card-body">
                                    <h1 class="card-title pricing-card-title">$ ${p.precio}0</h1>
                                    <ul class="list-unstyled mt-3 mb-4">
                                        <li> ${p.marca}</li>
                                        <li> ${p.descripcion}</li>                                        
                                        <li>Cantidad: ${p.stock}</li>
                                    </ul>
                                    <button type="button" class="btn btn-lg btn-block btn-primary">Comprar</button>
                                </div>
                            </div>                            
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="row justify-content-md-center">
            <a href="Facturas?modo=sumar&cantidad=${cantidad + 7}" class="btn btn-info">Ver mas</a>
        </div>
    </body>
</html>
