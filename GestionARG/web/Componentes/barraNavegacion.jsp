<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand">SoftCba</a>       
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link <c:if test="${activar == 1}">active</c:if>" href="Login">Inicio <span class="sr-only">(current)</span></a>
                </li>
            <c:if test="${admin}">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${activar == 2}">active</c:if>" href="Alumnos">Alumnos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 3}">active</c:if>" href="Cursos">Cursos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 4}">active</c:if>" href="Inscripciones">Inscripciones</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <c:if test="${activar == 5}">active</c:if>" href="Reportes">Reportes</a>
                    </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link <c:if test="${activar == 6}">active</c:if>" href="Programas">Programas</a>
                </li>                
            </ul>               
        </div>
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"> 
                <a class="nav-link" href="Login?modo=logearse">${mostrar}</a>
        </li>
    </ul>
</nav>
