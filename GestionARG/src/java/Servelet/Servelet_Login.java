package Servelet;

import Gestor.Gestor_Usuarios;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");

        if (modo != null) {
            if (modo.equals("iniciarSesion")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            } else if (modo.equals("registrarse")) {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/registrarUsuario.jsp");
                rd.forward(request, response);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
            rd.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String alias = request.getParameter("txtAlias");
        String contrasenia = request.getParameter("txtContrasenia");

        if (gu.obtenerUsuario(alias, contrasenia)) {
            request.getSession().setAttribute("admin", true);
            request.getSession().setAttribute("alias", alias);
            request.getSession().setAttribute("contrasenia", contrasenia);
            request.getSession().setAttribute("mostrar", "Bienvenido " + alias);
            request.getSession().setAttribute("activar", 1);
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
