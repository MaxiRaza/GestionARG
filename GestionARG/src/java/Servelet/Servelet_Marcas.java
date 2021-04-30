package Servelet;

import Gestor.Gestor_Marcas;
import Modelo.Marca;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Marcas", urlPatterns = {"/Marcas"})
public class Servelet_Marcas extends HttpServlet {

    Gestor_Marcas gm = new Gestor_Marcas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Marca m = new Marca();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
