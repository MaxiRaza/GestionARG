<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "java.util.*" %> 
<%@page import= "Modelo.Tipo_Cliente" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <jsp:include page="../Componentes\formato.jsp"/>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\modal.jsp"/>
        <title>GestionARG - Listado de Contactos</title>     
    </head>
    <body>             
        <br><h1  class="row justify-content-md-center">Listado de Contactos</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="Contactos?modo=AM" class="btn btn-info">Registrar Contacto</a><br><br><br> 
        </div>
        <div class="container-fluid">   
            <table class="table table-striped table-dark">
                <thead>
                    <tr class="table-primary">
                        <th scope="col">#</th>
                        <th scope="col">Teléfono</th>
                        <th scope="col">Correo</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach begin="0" end="${cantidad}" items="${listadoContactos}" var="c">
                        <tr>
                            <td> ${c.id_contacto}  </td>
                            <td> ${c.telefono}  </td>
                            <td> ${c.correo}  </td>
                            <td><a href="Contactos?modo=AM&id_contacto=${c.id_contacto}" class="btn btn-warning">Editar</a></td>
                            <td><a href="Contactos?modo=eliminar&a=a&id=${c.id_contacto}" class="btn btn-danger">Eliminar</a></td>                                                                                                 
                        </tr> 
                    </c:forEach>                    
                </tbody>
            </table>   
        </div>
        <jsp:include page="../Componentes\limiteLista.jsp"/>
    </body>
</html>