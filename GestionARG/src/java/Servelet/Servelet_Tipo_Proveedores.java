package Servelet;

import Gestor.Gestor_Tipo_Proveedores;
import Modelo.Tipo_Proveedor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Tipo_Proveedores", urlPatterns = {"/Tipo_Proveedores"})
public class Servelet_Tipo_Proveedores extends HttpServlet {

    Gestor_Tipo_Proveedores gtp = new Gestor_Tipo_Proveedores();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Tipo_Proveedor tp = new Tipo_Proveedor();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
