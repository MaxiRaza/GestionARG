package Servelet;

import Gestor.Gestor_Detalle_Facturas;
import Modelo.Detalle_Factura;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Detalle_Facturas", urlPatterns = {"/Detalle_Facturas"})
public class Servelet_Detalle_Facturas extends HttpServlet {

    Gestor_Detalle_Facturas gdf = new Gestor_Detalle_Facturas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            Detalle_Factura df = new Detalle_Factura();
        
    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }
    
}
