package Servelet;

import Gestor.Gestor_Detalle_Encargos;
import Modelo.Detalle_Encargo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Detalle_Encargos", urlPatterns = {"/Detalle_Encargos"})
public class Servelet_Detalle_Encargos extends HttpServlet {

    Gestor_Detalle_Encargos gde = new Gestor_Detalle_Encargos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Detalle_Encargo de = new Detalle_Encargo();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
