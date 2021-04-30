package Servelet;

import Gestor.Gestor_Clasificaciones;
import Modelo.Clasificacion;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Clasificaciones", urlPatterns = {"/Clasificaciones"})
public class Servelet_Clasificaciones extends HttpServlet {

    Gestor_Clasificaciones gc = new Gestor_Clasificaciones();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Clasificacion c = new Clasificacion();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
