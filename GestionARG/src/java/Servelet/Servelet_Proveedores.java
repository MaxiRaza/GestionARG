package Servelet;

import Gestor.Gestor_Clasificaciones;
import Gestor.Gestor_Contactos;
import Gestor.Gestor_Proveedores;
import Gestor.Gestor_Tipo_Proveedores;
import Modelo.Contacto;
import Modelo.DTO.DTO_Proveedor;
import Modelo.Proveedor;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Proveedores", urlPatterns = {"/Proveedores"})
public class Servelet_Proveedores extends HttpServlet {

    Gestor_Proveedores gp = new Gestor_Proveedores();
    Gestor_Contactos gc = new Gestor_Contactos();
    Gestor_Tipo_Proveedores gtc = new Gestor_Tipo_Proveedores();
    Gestor_Clasificaciones gcl = new Gestor_Clasificaciones();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {
                request.getSession().setAttribute("activar", 3);
                request.setAttribute("listadoProveedores", gp.obtenerProveedoresDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/listado_Proveedores.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_proveedor") != null) {
                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
                DTO_Proveedor p = gp.obtenerProveedorDTO(id_proveedor);
                request.setAttribute("proveedor", p);
            }
            request.setAttribute("listadoTipos", gtc.obtenerTipoProveedores());
            request.setAttribute("listadoClasificaciones", gcl.obtenerClasificaciones());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/AM_Proveedor.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
            gp.eliminarProveedor(id_proveedor);
            request.setAttribute("listadoProveedores", gp.obtenerProveedoresDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/listado_Proveedores.jsp");
            rd.forward(request, response);

        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Proveedor p = new Proveedor();
        Contacto c = new Contacto();

        p.setId_proveedor(Integer.parseInt(request.getParameter("txtIdProveedor")));
        c.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        p.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        p.setNombre(request.getParameter("txtNombre"));
        p.setCuit(request.getParameter("txtCuit"));
        p.setDireccion(request.getParameter("txtDireccion"));
        c.setCorreo(request.getParameter("txtCorreo"));
        c.setTelefono(request.getParameter("txtTelefono"));
        p.setId_tipo_proveedor(Integer.parseInt(request.getParameter("cmbTipos")));
        p.setId_clasificacion(Integer.parseInt(request.getParameter("cmbClasificaciones")));

        if (p.getId_proveedor() == 0) {
            if (gc.obtenerIdContacto(c.getCorreo(), c.getTelefono()) == 0) {
                gc.agregarContacto(c);
            }
            p.setId_contacto(gc.obtenerIdContacto(c.getCorreo(), c.getTelefono()));
            gp.agregarProveedor(p);
        } else {
            gc.actualizarContacto(c);
            gp.actualizarProveedor(p);
        }

        request.setAttribute("listadoProveedores", gp.obtenerProveedoresDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/listado_Proveedores.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
