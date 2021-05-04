<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand">
        <Font size=5>Gestion<font color="#5379fa">ARG</font></font></a>       
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link <c:if test="${activar == 1}">active</c:if>" href="Login">Inicio <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 2}">active</c:if>" href="Productos">Productos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 3}">active</c:if>" href="Proveedores">Proveedores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 4}">active</c:if>" href="Clientes">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 5}">active</c:if>" href="Encargos">Encargos</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 5}">active</c:if>" href="Sucursales">Sucursales</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 6}">active</c:if>" href="Facturación">Facturación</a>
                </li>                
            </ul>               
        </div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"> 
                <a class="nav-link" href="Loginn?modo=iniciarSesion">${mostrar}</a>
        </li>
    </ul>
</nav>
