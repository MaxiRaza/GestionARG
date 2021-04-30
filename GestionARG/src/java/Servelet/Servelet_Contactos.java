package Servelet;

import Gestor.Gestor_Contactos;
import Modelo.Contacto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Contactos", urlPatterns = {"/Contactos"})
public class Servelet_Contactos extends HttpServlet {

    Gestor_Contactos gc = new Gestor_Contactos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Contacto c = new Contacto();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG ";
    }

}
