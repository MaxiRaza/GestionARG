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
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 1;
        request.getSession().setAttribute("servelet", "Facturas");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("t", false);
        request.getSession().setAttribute("co", false);

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 7);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gf.obtenerFacturasDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }

                request.setAttribute("listadoFacturas", gf.obtenerFacturasDTO());
                request.setAttribute("listadoDetalles", gdf.obtenerDetalleFacturasDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Facturas/listado_Facturas.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }

        } else if (modo.equals("limite")) {

            if (gf.obtenerFacturasDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "enable");
                request.getSession().setAttribute("db", "enabled");

            } else if (Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "disabled");
                request.getSession().setAttribute("db", "enable");

            } else {

                request.getSession().setAttribute("da", "enabled");
                request.getSession().setAttribute("db", "disabled");

            }

        } else if (modo.equals("tema")) {

                if (request.getParameter("color").equals("oscuro")) {

                    request.getSession().setAttribute("color", "claro");

                } else {

                    request.getSession().setAttribute("color", "oscuro");

                }

            }

        request.getSession().setAttribute("n", filas);
        if (request.getParameter("cantidad") != null) {
            request.getSession().setAttribute("cantidad", (Integer.parseInt(request.getParameter("cantidad"))));
        }
        request.setAttribute("listadoFacturas", gf.obtenerFacturasDTO());
        request.setAttribute("listadoDetalles", gdf.obtenerDetalleFacturasDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Facturas/listado_Facturas.jsp");
        rd.forward(request, response);

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
