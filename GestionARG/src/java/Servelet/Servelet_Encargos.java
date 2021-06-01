package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Encargos;
import Gestor.Gestor_Productos;
import Gestor.Gestor_Proveedores;
import Gestor.Gestor_Detalle_Encargos;
import Gestor.Gestor_Marcas;
import Modelo.Detalle_Encargo;
import Modelo.Encargo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Encargos", urlPatterns = {"/Encargos"})
public class Servelet_Encargos extends HttpServlet {

    Gestor_Encargos ge = new Gestor_Encargos();
    Gestor_Productos gp = new Gestor_Productos();
    Gestor_Proveedores gpr = new Gestor_Proveedores();
    Gestor_Detalle_Encargos gde = new Gestor_Detalle_Encargos();
    Gestor_Productos gpro = new Gestor_Productos();
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Categorias gc = new Gestor_Categorias();
    boolean b = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {
                request.getSession().setAttribute("activar", 5);
                request.setAttribute("listadoEncargos", ge.obtenerEncargos());
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_detalle_encargo") != null) {
                request.getSession().setAttribute("modificar", true);
                b = false;
                request.getSession().setAttribute("accion", "Editar Detalle");
                int id_detalle_encargo = Integer.parseInt(request.getParameter("id_detalle_encargo"));
                Detalle_Encargo de = gde.obtenerDetalleEncargo(id_detalle_encargo);
                request.setAttribute("detalle_encargo", de);
            } else if (request.getParameter("id_encargo") != null) {
                b = true;
                request.getSession().setAttribute("accion", "Agregar Detalle");
                request.setAttribute("id", request.getParameter("id_encargo"));
                request.setAttribute("b", true);
            } else {
                b = false;
                request.getSession().setAttribute("accion", "Registrar Encargo");
                request.setAttribute("b", false);
            }

            request.setAttribute("listadoProductos", gp.obtenerProductos());
            request.setAttribute("listadoProveedores", gpr.obtenerProveedores());
            request.setAttribute("listadoMarcas", gm.obtenerMarcas());
            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/AM_Encargo.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("id_encargo") != null) {
                ge.eliminarEncargo(Integer.parseInt(request.getParameter("id_encargo")));
            } else {
                gde.eliminarDetalleEncargo(Integer.parseInt(request.getParameter("id_detalle_encargo")));
            }
            request.setAttribute("listadoEncargos", ge.obtenerEncargos());
            request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Encargo e = new Encargo();
        Detalle_Encargo de = new Detalle_Encargo();
        SimpleDateFormat d = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");

        e.setId_encargo(Integer.parseInt(request.getParameter("txtIdEncargo")));
        de.setId_detalle_encargo(Integer.parseInt(request.getParameter("txtIdDetalleEncargo")));
        e.setFecha(d.format(new Date()));
        de.setId_proveedor(Integer.parseInt(request.getParameter("cmbProveedores")));
        de.setId_producto(Integer.parseInt(request.getParameter("cmbProductos")));
        de.setCantidad(Float.parseFloat(request.getParameter("txtCantidad")));
        de.setImporte(gpro.obtenerProducto(de.getId_producto()).getPrecio() * de.getCantidad());

        if (e.getId_encargo() == 0) {

            ge.agregarEncargo(e);
            de.setId_encargo(ge.obtenerUltimoIdEncargo());
            gde.agregarDetalleEncargo(de);

        } else if (b) {

            de.setId_encargo(e.getId_encargo() / 10);
            gde.agregarDetalleEncargo(de);

        } else {

            de.setId_encargo(e.getId_encargo());
            gde.actualizarDetalleEncargo(de);

        }

        request.setAttribute("listadoEncargos", ge.obtenerEncargos());
        request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
