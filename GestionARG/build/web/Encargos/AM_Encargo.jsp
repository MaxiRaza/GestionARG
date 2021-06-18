<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="detalle_encargo" class="Modelo.DTO.DTO_Encargo" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} </title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} </h1><br>
        <form method="Post" action="Encargos">
            <div class="container">
                <div class="row justify-content-md-center">
                    <div class="col col-md-4">
                        <input type="hidden" name="txtIdEncargo" value="<c:if test="${b}" >${id}</c:if><jsp:getProperty name="detalle_encargo" property="id_encargo"></jsp:getProperty>" />
                        <input type="hidden" name="txtIdDetalleEncargo"  value="<jsp:getProperty name="detalle_encargo" property="id_detalle_encargo"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Categoria (*)</label>
                                <select name="cmbCategorias" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoCategorias}" var="c">
                                    <option value="${c.id_categoria}" <c:if test="${c.id_categoria == detalle_encargo.id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                                </c:forEach>            
                            </select>                             
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Marca (*)</label>
                            <select name="cmbMarcas" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoMarcas}" var="m">
                                    <option value="${m.id_marca}" <c:if test="${m.id_marca == detalle_encargo.id_marca}"> selected </c:if> > ${m.nombre} </option>
                                </c:forEach>             
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Producto (*)</label>
                            <select name="cmbProductos" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoProductos}" var="p">
                                    <option value="${p.id_producto}" <c:if test="${p.id_producto == detalle_encargo.id_producto}"> selected </c:if> > ${p.nombre} </option>
                                </c:forEach>             
                            </select>                                   
                        </div>
                    </div>
                    <div class="col col-sm-1">
                        <div class="form-group">
                            <label class="col-form-label">.</label>
                            <button type="submit" class="btn btn-info">Filtar</button>  
                        </div>
                        <div class="form-group">
                            <label class="col-form-label">.</label>
                            <button type="submit" class="btn btn-info">Filtar</button>  
                        </div>
                        <div class="form-group">      
                            <label class="col-form-label">.</label>
                            <button type="submit" class="btn btn-info">Filtar</button>  
                        </div>    
                    </div>    
                    <div class="col col-md-4">                  
                        <div class="form-group">
                            <label class="col-form-label">Cantidad (*)</label>
                            <input type="number" name="txtCantidad" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="detalle_encargo" property="cantidad"></jsp:getProperty>" </c:if>>
                                </div>
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Proveedor (*)</label>
                                    <select name="cmbProveedores" required="required" class="form-control" id="recipient-name" >
                                <c:forEach items="${listadoProveedores}" var="p">
                                    <option value="${p.id_proveedor}" <c:if test="${p.id_proveedor == detalle_encargo.id_proveedor}"> selected </c:if> > ${p.nombre} </option>
                                </c:forEach>             
                            </select>
                        </div>
                    </div>
                </div>
            </div><br>
            <div class="row justify-content-md-center" >
                <div class="form-group">
                    <a href="Encargos" class="btn btn-primary">Volver al listado</a>
                    <button type="submit" class="btn btn-success">${accion}</button>  
                </div>
            </div>
        </form>
    </body>
</html>