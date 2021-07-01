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
                <div class="col col-xl-8" style="margin-top: 55px">
                    <div class="jumbotron">
                        <h1 class="display-2">Bienvenido a Gestión<font color="#5379fa">ARG</font> !</h1><br>
                        <p class="lead">
                            Bienvenido a Gestión<font color="#5379fa">ARG</font>, proporcionado por "RazaCode´s". Nos complace ofrecerle 
                            acceso una herramienta web de gestión integral destinada a empresas o micro emprendimientos que buscan 
                            migrar su información a un entorno digital, accesible y seguro.
                        </p>       
                        <p class="lead">
                            Con la ayuda de nuestra herramienta podrán llevar de manera eficiente un control financiero sobre todas sus 
                            ventas, podrán controlar el stock disponible, consultar sobre los distintos proveedores para así generar y visualizar
                            el seguimiento de encargos, también podrá obtener información sobre sus clientes y por último contará con un 
                            apartado estadístico donde se plasmará toda la información obtenida la cual le brindará un importante valor 
                            agregado a la toma de decisiones.
                        </p>
                        <p class="lead">
                            Nuestra aplicación está en constante crecimiento y desarrollo recopilando toda la información obtenida de sus 
                            usuarios, nos adaptamos a su rubro y sus necesidades para lograr cumplir con sus expectativas.
                        </p>
                    </div>
                </div> 
                <div class="col col-xl-4" style="margin-top: 55px">
                    <div id="carouselExampleInterval" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" data-interval="10000">
                                <img src="Imagenes/c.jpg" class="d-block w-100" alt="" height="530">
                            </div>
                            <div class="carousel-item" data-interval="4000">
                                <img src="Imagenes/a.jpg" class="d-block w-100" alt="" height="530">
                            </div>
                            <div class="carousel-item" data-interval="8000">
                                <img src="Imagenes/b.jpeg" class="d-block w-100" alt="" height="530">
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
                <div class="row align-items-md-stretch">
                    <div class="col-md-6">
                        <div class="h-100 p-5  jumbotron rounded-3">
                            <h2>Nueva Funcionalidad de Temas <i class="bi bi-brightness-high-fill"></i></h2>
                            <p>
                                Nos complace anunciar que ya se puede disfrutar de nuestra última mejora que les permite a los usuarios
                                intercambiar entre un tema oscuro y un tema claro, de esta forma mejoramos la experiencia de usuario y 
                                adaptándonos a sus sugerencias.
                            </p>
                            <p>
                                ¡Muchas gracias por seguir confiando en nosotros!
                            </p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="h-100 p-5  jumbotron rounded-3">
                            <h2>La plataforma de "MercadoPago" esta en camino !</h2>
                            <p>
                                Les informamos que nuestro equipo de desarrollo ya está trabajando en la nueva forma de pago que permitirá
                                a los clientes poder abonar de manera online mediante dicha plataforma.
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
