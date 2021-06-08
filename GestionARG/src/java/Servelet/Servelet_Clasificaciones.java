package Servelet;

import Gestor.Gestor_Clasificaciones;
import Modelo.Clasificacion;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Clasificaciones", urlPatterns = {"/Clasificaciones"})
public class Servelet_Clasificaciones extends HttpServlet {

    Gestor_Clasificaciones gc = new Gestor_Clasificaciones();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 16);
                request.setAttribute("listadoClasificaciones", gc.obtenerClasificaciones());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clasificaciones/listado_Clasificaciones.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_clasificacion") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_clasificacion = Integer.parseInt(request.getParameter("id_clasificacion"));
                Clasificacion c = gc.obtenerClasificacion(id_clasificacion);
                request.setAttribute("clasificacion", c);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clasificaciones/AM_Clasificacion.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_clasificacion = Integer.parseInt(request.getParameter("id_clasificacion"));
            gc.eliminarClasificacion(id_clasificacion);
            request.setAttribute("listadoClasificaciones", gc.obtenerClasificaciones());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clasificaciones/listado_Clasificaciones.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Clasificacion c = new Clasificacion();

        c.setId_clasificacion(Integer.parseInt(request.getParameter("txtIdClasificacion")));
        c.setNombre(request.getParameter("txtNombre"));

        if (c.getId_clasificacion() == 0) {
            gc.agregarClasificacion(c);
        } else {
            gc.actualizarClasificacion(c);
        }

        request.setAttribute("listadoClasificaciones", gc.obtenerClasificaciones());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clasificaciones/listado_Clasificaciones.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
