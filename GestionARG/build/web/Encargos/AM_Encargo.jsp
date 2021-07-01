<%@page import= "java.util.*" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:useBean id="detalle_encargo" class="Modelo.DTO.DTO_Encargo" scope="request"></jsp:useBean>
        <title>GestionARG - ${accion} </title>
    </head>
    <body>      
        <br><h1 class="row justify-content-md-center">${accion} </h1><br>
        <c:if test="${lista}">
            <div  style="float: right; padding-right: 10%">
                <table class="table table-striped table-dark">
                    <thead>
                        <tr class="table-primary">
                            <th>#</th>
                            <th>Proveedor </th>
                            <th>Producto</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listadoDetalles}" var="d">
                            <tr>
                                <th>${d.id_detalle_encargo}</th>
                                <th>${d.proveedor}</th>
                                <td>${d.producto}</td>
                                <td>${d.cantidad}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
        <form method="Post" action="Encargos">
            <div class="container">
                <div class="row justify-content-md-center">                  
                    <div class="col col-4">
                        <input type="hidden" name="txtIdEncargo" value="<c:if test="${f}" >${id_en}</c:if><c:if test="${!f}" ><jsp:getProperty name="detalle_encargo" property="id_encargo"></jsp:getProperty></c:if>" />
                        <input type="hidden" name="txtIdDetalleEncargo"  value="<jsp:getProperty name="detalle_encargo" property="id_detalle_encargo"></jsp:getProperty>" />
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Categoria (*)</label>
                                <select name="cmbCategorias" required="required" class="form-control" id="recipient-name" >
                                    <option ${op1}>Seleccionar...</option>
                                <c:forEach items="${listadoCategorias}" var="c">
                                    <option value="${c.id_categoria}" <c:if test="${c.id_categoria == detalle_encargo.id_categoria}"> selected </c:if>> ${c.nombre} </option>                  
                                </c:forEach>         
                            </select>
                        </div> 
                        <c:if test="${aa}">                          
                            <div class="form-group">
                                <label for="recipient-name" class="col-form-label">Marca (*)</label>
                                <select name="cmbMarcas" required="required" class="form-control" id="recipient-name">
                                    <option ${op2}>Seleccionar...</option>
                                    <c:forEach items="${listadoMarcas}" var="m">
                                        <option value="${m.id_marca}" <c:if test="${m.id_marca == detalle_encargo.id_marca}"> selected </c:if>> ${m.nombre} </option>
                                    </c:forEach>                                   
                                </select>
                            </div>
                            <c:if test="${bb}">                              
                                <div class="form-group">
                                    <label for="recipient-name" class="col-form-label">Producto (*)</label>
                                    <select name="cmbProductos" required="required" class="form-control" id="recipient-name">
                                        <option ${op3}>Seleccionar...</option>
                                        <c:forEach items="${listadoProductos}" var="p">
                                            <option value="${p.id_producto}" <c:if test="${p.id_producto == detalle_encargo.id_producto}"> selected </c:if>> ${p.nombre} </option>
                                        </c:forEach>             
                                    </select>                                   
                                </div>
                                <c:if test="${cc}">
                                    <div class="form-group">
                                        <label for="recipient-name" class="col-form-label">Proveedor (*)</label>
                                        <select name="cmbProveedores" required="required" class="form-control" id="recipient-name">
                                            <option ${op4}>Seleccionar...</option>
                                            <c:forEach items="${listadoProveedores}" var="p">
                                                <option value="${p.id_proveedor}" <c:if test="${p.id_proveedor == detalle_encargo.id_proveedor}"> selected </c:if>> ${p.nombre} </option>
                                            </c:forEach>             
                                        </select>
                                    </div>
                                    <c:if test="${dd}">        
                                        <div class="form-group">
                                            <label class="col-form-label">Cantidad (*)</label>
                                            <input type="number" name="txtCantidad" required="required" class="form-control" id="recipient-name" <c:if test="${modificar}"> value="<jsp:getProperty name="detalle_encargo" property="cantidad"></jsp:getProperty>" </c:if>>
                                                </div>
                                    </c:if>
                                    <c:if test="${ee}">        
                                        <div class="form-group">
                                            <label for="recipient-name" class="col-form-label">Estado (*)</label>
                                            <select name="cmbEstados" required="required" class="form-control" id="recipient-name">
                                                <option>Seleccionar...</option>
                                                <c:forEach items="${listadoEstados}" var="e">
                                                    <option value="${e.id_estado}" <c:if test="${e.id_estado == id_estado}"> selected </c:if>> ${e.nombre} </option>
                                                </c:forEach>             
                                            </select>
                                        </div>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="col col-2" style="padding-top:  3%">
                        <div class="form-group">
                            <button type="submit" class="btn btn-info" style="display: ${btn1}"><i class="bi bi-arrow-down" style="font-size: 18px"></i></button>  
                        </div>              
                    </div>
                </div><br>
                <jsp:include page="../Componentes\botonesAM.jsp"/>      
            </div>
        </form>
    </body>
</html>