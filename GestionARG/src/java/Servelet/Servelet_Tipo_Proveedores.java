package Servelet;

import Gestor.Gestor_Tipo_Proveedores;
import Modelo.Tipo_Proveedor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
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
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 3;
        request.getSession().setAttribute("servelet", "Tipo_Proveedores");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 11);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gtp.obtenerTipoProveedores().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoTiposProveedores", gtp.obtenerTipoProveedores());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Proveedores/listado_Tipo_Proveedores.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_tipo_proveedor") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_tipo_proveedor = Integer.parseInt(request.getParameter("id_tipo_proveedor"));
                Tipo_Proveedor tp = gtp.obtenerTipoProveedor(id_tipo_proveedor);
                request.setAttribute("tipo_proveedor", tp);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Proveedores/AM_Tipo_Proveedor.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);

                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", "el tipo de proveedor " + gtp.obtenerTipoProveedor(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gtp.eliminarTipoProveedor(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

        } else if (modo.equals("limite")) {

            if (gtp.obtenerTipoProveedores().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoTiposProveedores", gtp.obtenerTipoProveedores());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Proveedores/listado_Tipo_Proveedores.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Tipo_Proveedor tp = new Tipo_Proveedor();

        tp.setId_tipo_proveedor(Integer.parseInt(request.getParameter("txtIdTipoProveedor")));
        tp.setNombre(request.getParameter("txtNombre"));

        if (tp.getId_tipo_proveedor() == 0) {
            gtp.agregarTipoProveedor(tp);
        } else {
            gtp.actualizarTipoProveedor(tp);
        }

        request.setAttribute("listadoTiposProveedores", gtp.obtenerTipoProveedores());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Proveedores/listado_Tipo_Proveedores.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
