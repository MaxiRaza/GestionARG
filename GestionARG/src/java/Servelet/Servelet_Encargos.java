package Servelet;

import Gestor.Gestor_Encargos;
import Modelo.Encargo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Encargos", urlPatterns = {"/Encargos"})
public class Servelet_Encargos extends HttpServlet {

    Gestor_Encargos ge = new Gestor_Encargos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Encargo e = new Encargo();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
