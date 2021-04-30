package Servelet;

import Gestor.Gestor_Proveedores;
import Modelo.Proveedor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Proveedores", urlPatterns = {"/Proveedores"})
public class Servelet_Proveedores extends HttpServlet {

    Gestor_Proveedores gp = new Gestor_Proveedores();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Proveedor p = new Proveedor();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
