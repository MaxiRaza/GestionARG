package Servelet;

import Gestor.Gestor_Sucursales;
import Modelo.Sucursal;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Sucursales", urlPatterns = {"/Sucursales"})
public class Servelet_Sucursales extends HttpServlet {

    Gestor_Sucursales gs = new Gestor_Sucursales();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Sucursal s = new Sucursal();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
