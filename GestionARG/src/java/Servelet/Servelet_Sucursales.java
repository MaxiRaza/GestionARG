package Servelet;

import Gestor.Gestor_Sucursales;
import Modelo.Sucursal;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Sucursales");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 6);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gs.obtenerSucursales().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoSucursales", gs.obtenerSucursales());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Sucursales/listado_Sucursales.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_sucursal") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_sucursal = Integer.parseInt(request.getParameter("id_sucursal"));
                Sucursal s = gs.obtenerSucursal(id_sucursal);
                request.setAttribute("sucursal", s);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Sucursales/AM_Sucursal.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " la sucursal " + gs.obtenerSucursal(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gs.eliminarSucursal(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoSucursales", gs.obtenerSucursales());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Sucursales/listado_Sucursales.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gs.obtenerSucursales().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "enable");
                request.getSession().setAttribute("db", "enabled");

            } else if (Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "disabled");
                request.getSession().setAttribute("db", "enable");

            } else {

                request.getSession().setAttribute("da", "enabled");
                request.getSession().setAttribute("db", "disabled");

            }

        }

        request.getSession().setAttribute("n", filas);
        request.getSession().setAttribute("cantidad", (Integer.parseInt(request.getParameter("cantidad"))));
        request.setAttribute("listadoSucursales", gs.obtenerSucursales());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Sucursales/listado_Sucursales.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Sucursal s = new Sucursal();

        s.setId_sucursal(Integer.parseInt(request.getParameter("txtIdSucursal")));
        s.setNombre(request.getParameter("txtNombre"));
        s.setDireccion(request.getParameter("txtDireccion"));

        if (s.getId_sucursal() == 0) {
            gs.agregarSucursal(s);
        } else {
            gs.actualizarSucursal(s);
        }

        request.setAttribute("listadoSucursales", gs.obtenerSucursales());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Sucursales/listado_Sucursales.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
