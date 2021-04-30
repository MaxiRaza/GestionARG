package Servelet;

import Gestor.Gestor_Facturas;
import Modelo.Factura;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Facturas", urlPatterns = {"/Facturas"})
public class Servelet_Facturas extends HttpServlet {

    Gestor_Facturas gf = new Gestor_Facturas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Factura f = new Factura();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
