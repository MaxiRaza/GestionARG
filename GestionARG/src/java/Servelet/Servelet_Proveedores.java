package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Clasificaciones;
import Gestor.Gestor_Contactos;
import Gestor.Gestor_Marcas;
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
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Categorias ga = new Gestor_Categorias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Proveedores");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {
                request.getSession().setAttribute("activar", 3);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gp.obtenerProveedoresDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }

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
                DTO_Proveedor p = gp.obtenerProveedorDTO(Integer.parseInt(request.getParameter("id_proveedor")));
                request.setAttribute("proveedor", p);

            }

            request.setAttribute("listadoCategorias", ga.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/FiltroProveedores.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el proveedor " + gp.obtenerProveedor(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gp.eliminarProveedor(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoProveedores", gp.obtenerProveedoresDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/listado_Proveedores.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gp.obtenerProveedoresDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoProveedores", gp.obtenerProveedoresDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/listado_Proveedores.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("cmbCategorias") != null) {

            if (request.getParameter("txtIdProveedor") != null && !request.getParameter("txtIdProveedor").equals("0")) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                DTO_Proveedor p = gp.obtenerProveedorDTO(Integer.parseInt(request.getParameter("txtIdProveedor")));
                request.setAttribute("proveedor", p);

            }

            request.setAttribute("listadoTipos", gtc.obtenerTipoProveedores());
            request.setAttribute("listadoClasificaciones", gcl.obtenerClasificaciones());
            request.setAttribute("listadoMarcas", gm.obtenerMarcasFiltro(Integer.parseInt(request.getParameter("cmbCategorias"))));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Proveedores/AM_Proveedor.jsp");
            rd.forward(request, response);
            return;

        }

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
        p.setId_marca(Integer.parseInt(request.getParameter("cmbMarcas")));

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
