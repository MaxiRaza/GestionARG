<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="producto" class="Modelo.DTO.DTO_Producto" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} Producto</title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} Producto</h1><br>
        <form method="Post" action="Productos">
            <div class="container">
                <div class="row justify-content-md-center">
                    <div class="col col-md-4 ">
                        <input type="hidden" name="txtIdProducto" value="<jsp:getProperty name="producto" property="id_producto"></jsp:getProperty>" />
                            <div class="form-group">
                                <div class="form-group">
                                    <label class="col-form-label">Código (*)</label>
                                    <input type="text" name="txtCodigo" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="codigo"></jsp:getProperty>" </c:if>>
                                    </div>
                                    <label for="recipient-name" class="col-form-label">Nombre (*)</label>
                                    <input type="text" name="txtNombre" required="required" class="form-control" id="recipient-name"  <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="nombre"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Fecha de Elaboración (*)</label>
                                    <input type="date" name="txtFechaElaboracion" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="fecha_fab"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Fecha de Vencimiento (*)</label>
                                    <input type="date" name="txtFechaVencimiento" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="fecha_ven"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Precio (*)</label>
                                    <input type="number" name="txtPrecio" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="precio"></jsp:getProperty>" </c:if>>
                                </div>
                            </div>
                            <div class="col col-md-4">
                                <div class="form-group">
                                    <label class="col-form-label">Descripción </label>
                                    <input type="text" name="txtDescripcion" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="descripcion"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label class="col-form-label">Cantidad (*)</label>
                                    <input type="number" name="txtCantidad" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="producto" property="stock"></jsp:getProperty>" </c:if>>
                                </div>                                                                   
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Marca (*)</label>
                                    <select name="cmbMarcas" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoMarcas}" var="m">
                                    <option value="${m.id_marca}" <c:if test="${m.id_marca == producto.id_marca}"> selected </c:if> > ${m.nombre} </option>
                                </c:forEach>             
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Ubicación en Depósito (*)</label>
                            <select name="cmbDepositos" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoDepositos}" var="d">
                                    <option value="${d.id_deposito}" <c:if test="${d.id_deposito == producto.id_deposito}"> selected </c:if> > ${d.ubicacion} </option>
                                </c:forEach>             
                            </select>
                        </div> 
                    </div>
                </div><br>
                <div class="row justify-content-md-center" >
                    <div class="col-md-2">
                        <a href="Productos?modo=AM&accion=${accion}&id_producto=${producto.id_producto}" class="btn btn-primary" style="width: 120px"><i class="bi bi-reply-fill" style="font-size: 22px"></i></a>
                    </div>  
                    <div class="col-md-2">
                        <button type="submit" class="btn btn-success" style="width: 120px"><i class="bi bi-save2" style="font-size: 22px"></i></button>  
                    </div>  
                </div>
            </div>
        </form>
    </body>
</html>