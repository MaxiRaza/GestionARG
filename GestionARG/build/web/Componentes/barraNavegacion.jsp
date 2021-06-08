<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand">     
        <Font size=5>Gestion<font color="#5379fa">ARG</font></font></a>      
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <c:if test="${admin}">
                    <a class="nav-link <c:if test="${activar == 1}">active</c:if>" href="Loginn">Inicio <span class="sr-only">(current)</span></a>
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
                        <a class="nav-link <c:if test="${activar == 7}">active</c:if>" href="Facturas">Facturación</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 8}">active</c:if>" href="Usuarios">Usuarios</a>
                    </li>                    
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 9}">active</c:if>" href="Compras">Comprar</a>
                    </li>
                    <ul class="navbar-nav mr-auto">           
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Desplegables</a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="nav-link <c:if test="${activar == 6}">active</c:if>" href="Sucursales">Sucursales</a>
                            <a class="nav-link <c:if test="${activar == 10}">active</c:if>" href="Tipo_Clientes">Tipos de Cliente</a>
                            <a class="nav-link <c:if test="${activar == 11}">active</c:if>" href="Tipo_Proveedores">Tipos de Proveedores</a>
                            <a class="nav-link <c:if test="${activar == 12}">active</c:if>" href="Roles">Roles</a>
                            <a class="nav-link <c:if test="${activar == 13}">active</c:if>" href="Marcas">Marcas</a>
                            <a class="nav-link <c:if test="${activar == 14}">active</c:if>" href="Formas_de_Pagos">Formas de Pagos</a>
                            <a class="nav-link <c:if test="${activar == 15}">active</c:if>" href="Depositos">Depósitos</a>
                            <a class="nav-link <c:if test="${activar == 16}">active</c:if>" href="Clasificaciones">Clasificaciones</a>
                            <a class="nav-link <c:if test="${activar == 17}">active</c:if>" href="Categorias">Categorias</a>
                            <a class="nav-link <c:if test="${activar == 18}">active</c:if>" href="Contactos">Contactos</a>
                            </div>
                        </li>
                    </ul>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 19}">active</c:if>" href="Reportess">Reportes</a>
                    </li>
                </ul>               
            </div>
            <ul class="navbar-nav mr-auto">           
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${mostrar}
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="Loginn?modo=iniciarSesion">Cerrar Sesion</a>
                </div>
            </li>
        </ul>
    </c:if>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>  
