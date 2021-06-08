package Servelet;

import Gestor.Gestor_Detalle_Facturas;
import Gestor.Gestor_Facturas;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Facturas", urlPatterns = {"/Facturas"})
public class Servelet_Facturas extends HttpServlet {

    Gestor_Facturas gf = new Gestor_Facturas();
    Gestor_Detalle_Facturas gdf = new Gestor_Detalle_Facturas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 7);
                request.setAttribute("listadoFacturas", gf.obtenerFacturasDTO());
                request.setAttribute("listadoDetalles", gdf.obtenerDetalleFacturasDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Facturas/listado_Facturas.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
