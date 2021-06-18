<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Inicio</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row ">
                <div class="col ">
                    <div class="jumbotron">
                        <h1 class="display-2">Bienvenido a Gestion<font color="#5379fa">ARG</font> !</h1><br>
                        <p class="lead">Bienvenido a Gestion<font color="#5379fa">ARG</font> proporcionado por "RazaCode´s". 
                            Nos complace ofrecerle acceso una herramienta web de gestion empresarial integral 
                            destinada a empresas que se encuentran en crecimiento o buscan migrar sus datos a un entorno
                            digital. Podrás llevar un control financiero sobre tus ventas, controlar el stock, informarte sobre tus clientes y consultar por proveedores. </p>       
                        <hr class="my-6">    
                    </div>
                </div> 
                <div class="col col-xl-4 ">
                    <div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" data-interval="10000">
                                <img src="Imagenes/c.jpg" class="d-block w-100" alt="" height="500">
                            </div>
                            <div class="carousel-item" data-interval="4000">
                                <img src="Imagenes/a.jpg" class="d-block w-100" alt="" height="500">
                            </div>
                            <div class="carousel-item" data-interval="8000">
                                <img src="Imagenes/b.jpeg" class="d-block w-100" alt="" height="500">
                            </div>
                        </div>
                        <a class="carousel-control-prev" type="button" data-target="#carouselExampleInterval" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Anterior</span>
                        </a>
                        <a class="carousel-control-next" type="button" data-target="#carouselExampleInterval" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Siguiente</span>
                        </a>
                    </div>
                </div> 
            </div>
        </div>
        <jsp:include page="../Componentes\PieDePagina.jsp"/>
    </body>
</html>
