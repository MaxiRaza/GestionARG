package Servelet;

import Gestor.Gestor_Roles;
import Gestor.Gestor_Usuarios;
import Modelo.EnviarCorreo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Loginn", urlPatterns = {"/Loginn"})
public class Servelet_Login extends HttpServlet {

    Gestor_Usuarios gu = new Gestor_Usuarios();
    Gestor_Roles gr = new Gestor_Roles();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("servelet", "Loginn");
         request.getSession().setAttribute("color", "claro");
         request.getSession().setAttribute("t", false);

        if (modo != null) {

            if (modo.equals("iniciarSesion")) {

                request.getSession().setAttribute("rol", 0);
                request.getSession().setAttribute("log", false);
                request.getSession().setAttribute("alias", "");
                request.getSession().setAttribute("contrasenia", "");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            } else if (modo.equals("registrarse")) {

                request.setAttribute("listadoRoles", gr.obtenerRoles());
                request.getSession().setAttribute("accion", "Registrar");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/AM_Usuario.jsp");
                rd.forward(request, response);

            } else if (modo.equals("tema")) {

                if (request.getParameter("color").equals("oscuro")) {

                    request.getSession().setAttribute("color", "claro");

                } else {

                    request.getSession().setAttribute("color", "oscuro");

                }

            } else if (modo.equals("recuperacion")) {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/recuperarContrasenia.jsp");
                rd.forward(request, response);

            }

        } else {

            request.getSession().setAttribute("t", false);
            request.getSession().setAttribute("activar", 1);

        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Inicio/inicio.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("txtCorreo") != null) {

            try {

                EnviarCorreo.enviarMail("smtp.gmail.com", "587", "gestionargcontacto@gmail.com", "kb4240423180", request.getParameter("txtCorreo"), "Recuperación de contraseña", "Usted solicito una recuperación de contraseña, su código es: " + gu.obtenerContraUsuario(request.getParameter("txtCorreo")));

            } catch (Exception e) {

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
            rd.forward(request, response);
            
        }

        String alias = request.getParameter("txtAlias");
        String contrasenia = request.getParameter("txtContrasenia");

        if (gu.obtenerUsuario(alias, contrasenia)) {

            request.getSession().setAttribute("color", "claro");
            request.getSession().setAttribute("log", true);
            request.getSession().setAttribute("usuario", gu.obtenerIdUsuario(alias, contrasenia));
            request.getSession().setAttribute("alias", alias);
            request.getSession().setAttribute("contrasenia", contrasenia);
            request.getSession().setAttribute("mostrar", "Bienvenido " + alias);
            request.getSession().setAttribute("activar", 1);

            switch (gu.obtenerRolUsuario(alias, contrasenia)) {
                case 1:
                    request.getSession().setAttribute("rol", 1);
                    break;
                case 4:
                    request.getSession().setAttribute("rol", 4);
                    break;
                case 6:
                    request.getSession().setAttribute("rol", 6);
                    break;
                default:
                    request.getSession().setAttribute("rol", 0);
            }

            request.getSession().setAttribute("t", false);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Inicio/inicio.jsp");
            rd.forward(request, response);

        } else {

            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}