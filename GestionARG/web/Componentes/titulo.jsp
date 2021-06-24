<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${t}">
    <head>
        <title>GestionARG - Listado de ${servelet}</title>
    </head>
    <body>
        <br><h1  class="row justify-content-md-center">Listado de ${servelet}</h1><br>
        <div style="padding-right: 75px" align = "right">
            <a href="${servelet}?modo=AM" class="btn btn-info" style="width: 100px"><i class="bi bi-plus-lg" style="font-size: 20px"></i></a><br><br><br> 
        </div>
    </body>   
</c:if>