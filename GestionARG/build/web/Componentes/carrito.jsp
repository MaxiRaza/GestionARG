<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="micarrito" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">Mi Carrito <i class="bi bi-cart-plus" style="font-size:  18px"></i></h4>                      
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Producto </th>
                            <th>Precio</th>
                            <th><a href="Compras?modo=carrito&v=v" href="" style="color: red">Vaciar</a></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listadoCarrito}" var="c">
                            <tr>
                                <th>${c.id_compra}</th>
                                <td>${c.nombre}</td>                             
                                <td>${c.precio}</td>
                                <td><a href="Compras?modo=carrito&BD=BD&id=${c.id_producto}" class="btn btn-danger" style="width: 50px"><i class="bi bi-trash" style="font-size:  18px"></i></a></td>
                            </tr>
                        </c:forEach>                                                   
                    </tbody>
                </table>
                <c:if test="${listadoCarrito.isEmpty()}">El carrito esta vacio aún !</c:if>                
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-warning" style="width: 120px" data-dismiss="modal"><i class="bi bi-x-circle" style="font-size:  18px"></i></button>
                    <a href="Compras?modo=comprar&usuario=${usuario}" class="btn btn-success <c:if test="${listadoCarrito.isEmpty()}">disabled</c:if>" style="width: 120px"><i class="bi bi-cash-coin" style="font-size:  18px"></i></a>
            </div>
        </div>
    </div>
</div>