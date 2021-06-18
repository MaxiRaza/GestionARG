<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Usuario" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="producto" class="Modelo.DTO.DTO_Producto" scope="request"></jsp:useBean>
        </head>
        <body>
            <br><h1  class="row justify-content-md-center">Listado de Articulos</h1><br><br>

            <form method="Post" action="Compras">
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
                        <a href="Compras?modo=Limpiar" class="btn btn-info">Limpiar</a>
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
                                    <a class="btn btn-lg btn-block btn-primary" data-toggle="modal" data-target="#carrito">Comprar</a>                                    
                                </div>
                            </div>                            
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>

        <div class="modal fade" id="carrito" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Mi Carrito</h5>
                        <button type="button" class="btn-close" data-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        ¿ Desea agregar este poducto a su carrito ?                       
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-lg btn-block btn-primary">Agregar</button>
                        <button type="button" class="btn btn-lg btn-block btn-secondary" data-dismiss="modal">Cancelar</button>                     
                    </div>
                </div>
            </div>
        </div>
        <!-- Button trigger modal-->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalAbandonedCart">Launch
            modal</button>

        <!-- Modal: modalAbandonedCart-->
        <div class="modal fade right" id="modalAbandonedCart" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true" data-backdrop="false">
            <div class="modal-dialog modal-side modal-bottom-right modal-notify modal-info" role="document">
                <!--Content-->
                <div class="modal-content">
                    <!--Header-->
                    <div class="modal-header">
                        <p class="heading">Productos en el carrito
                        </p>

                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true" class="white-text">&times;</span>
                        </button>
                    </div>

                    <!--Body-->
                    <div class="modal-body">

                        <div class="row">
                            <div class="col-3">
                                <p></p>
                                <p class="text-center"><i class="fas fa-shopping-cart fa-4x"></i></p>
                            </div>

                            <div class="col-9">
                                <p>Do you need more time to make a purchase decision?</p>
                                <p>No pressure, your product will be waiting for you in the cart.</p>
                            </div>
                        </div>
                    </div>

                    <!--Footer-->
                    <div class="modal-footer justify-content-center">
                        <a type="button" class="btn btn-info">Go to cart</a>
                        <a type="button" class="btn btn-outline-info waves-effect" data-dismiss="modal">Cancel</a>
                    </div>
                </div>
                <!--/.Content-->
            </div>
        </div>
        <!-- Modal: modalAbandonedCart-->

        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalCart">Launch modal</button>

        <!-- Modal: modalCart -->
        <div class="modal fade" id="modalCart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!--Header-->
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Mi Carrito</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <!--Body-->
                    <div class="modal-body">

                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Producto </th>
                                    <th>Precio</th>
                                    <th>Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <th scope="row">1</th>
                                    <td>Producto 1</td>
                                    <td>100$</td>
                                    <td><a><i class="fas fa-times"></i></a></td>
                                </tr>
                                <tr>
                                    <th scope="row">2</th>
                                    <td>Producto 2</td>
                                    <td>100$</td>
                                    <td><a><i class="fas fa-times"></i></a></td>
                                </tr>
                                <tr>
                                    <th scope="row">3</th>
                                    <td>Producto 3</td>
                                    <td>100$</td>
                                    <td><a><i class="fas fa-times"></i></a></td>
                                </tr>
                                <tr>
                                    <th scope="row">4</th>
                                    <td>Producto 4</td>
                                    <td>100$</td>
                                    <td><a><i class="fas fa-times"></i></a></td>
                                </tr>
                                <tr class="total">
                                    <th scope="row">5</th>
                                    <td>Total</td>
                                    <td>400$</td>
                                    <td></td>
                                </tr>
                            </tbody>
                        </table>

                    </div>
                    <!--Footer-->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cerrar</button>
                        <button class="btn btn-primary">Comprar</button>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
