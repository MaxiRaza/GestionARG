<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="min-height: 91%; min-width: 100%; position: relative;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <c:if test="${color == 'claro'}">
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/darkly/bootstrap.min.css" integrity="sha384-nNK9n28pDUDDgIiIqZ/MiyO3F4/9vsMtReZK39klb/MtkZI3/LtjSjlmyVPS3KdN" crossorigin="anonymous">
        </c:if>
        <c:if test="${color == 'oscuro'}">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootswatch@4.5.2/dist/flatly/bootstrap.min.css" integrity="sha384-qF/QmIAj5ZaYFAeQcrQ6bfVMAh4zZlrGwTPY7T/M+iTTLJqJBJjwwnsE5Y0mV7QK" crossorigin="anonymous">
        </c:if>
        <link href="CSS/misEstilos.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">      
        <link rel="shortcut icon" href="Imagenes/i.png" type="image/png" sizes="64x64"/>
    </head>
    <body style="margin: 0; margin-bottom: 40px;">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script> 
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <jsp:include page="../Componentes\PieDePagina.jsp"/>
        <jsp:include page="../Componentes\modalEliminacion.jsp"/>
        <jsp:include page="../Componentes\modalStock.jsp"/>
        <jsp:include page="../Componentes\titulo.jsp"/>
        <jsp:include page="../Componentes\carrito.jsp"/>
        <jsp:include page="../Componentes\modalConfirmacion.jsp"/>
    </body>
</html>
