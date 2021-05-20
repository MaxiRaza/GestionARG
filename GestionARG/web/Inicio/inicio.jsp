<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Componentes\formato.jsp"/>
        <title>GestionARG - Inicio</title>
    </head>
    <body>
        <jsp:include page="../Componentes\barraNavegacion.jsp"/>
        <div class="container-fluid">
            <div class="row ">
                <div class="col col-xl-8 ">
                    <div class="jumbotron">
                        <h1 class="display-2">Bienvenido a Gestion<font color="#5379fa">ARG</font> !</h1><br>
                        <p class="lead">Gestionar significa llevar adelante una empresa o proyecto, administrar o manejar una compañía, o liderar o conducir una situación específica. La palabra, como tal, deriva del sustantivo gestión.</p>
                        <p class="lead">En este sentido, gestionar implica ocuparse de la administración, organización, coordinación y funcionamiento de una empresa o compañía y de sus recursos humanos y económicos, con la finalidad de lograr un conjunto de objetivos concretos.</p>
                        <p class="lead">Asimismo, gestionar es liderar o dirigir un proyecto, tener la iniciativa y tomar las decisiones necesarias para su desarrollo.
                            Por otro lado, gestionar también se refiere al manejo o conducción de una situación problemática.</p>
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
    </body>
</html>
