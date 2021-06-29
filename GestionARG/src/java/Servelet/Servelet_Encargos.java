package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Encargos;
import Gestor.Gestor_Productos;
import Gestor.Gestor_Proveedores;
import Gestor.Gestor_Detalle_Encargos;
import Gestor.Gestor_Estados;
import Gestor.Gestor_Marcas;
import Modelo.DTO.DTO_Encargo;
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
    Gestor_Estados ges = new Gestor_Estados();
    boolean f = false;
    boolean check1 = false;
    boolean check2 = false;
    boolean check3 = false;
    boolean check4 = false;
    boolean check5 = false;
    boolean check6 = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 2;
        request.getSession().setAttribute("servelet", "Encargos");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");
        request.getSession().setAttribute("t", true);
        request.getSession().setAttribute("co", false);

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 5);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (ge.obtenerEncargos().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoEncargos", ge.obtenerEncargosDTO());
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            request.getSession().setAttribute("t", false);

            if (request.getParameter("id_detalle_encargo") != null) {

                f = false;
                request.setAttribute("lista", false);
                request.setAttribute("f", false);
                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar Detalle");
                DTO_Encargo de = gde.obtenerDetalleEncargoDTO(Integer.parseInt(request.getParameter("id_detalle_encargo")));
                request.setAttribute("detalle_encargo", de);
                request.setAttribute("listadoEstados", ges.obtenerEstados());
                request.setAttribute("id_estado", ge.obtenerEncargo(de.getId_encargo()).getId_estado());
                check1 = true;
                check2 = true;
                check3 = true;
                check4 = true;
                check5 = true;
                check6 = true;
                request.setAttribute("a", true);
                request.setAttribute("b", true);
                request.setAttribute("c", true);
                request.setAttribute("d", true);
                request.setAttribute("es", true);
                request.setAttribute("confirmar", "enabled");
                request.setAttribute("btn1", "none");

            } else if (request.getParameter("id_encargo") != null) {

                f = true;
                request.setAttribute("lista", true);
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosIdDTO(Integer.parseInt(request.getParameter("id_encargo"))));
                request.getSession().setAttribute("accion", "Agregar Detalle");
                request.setAttribute("id", request.getParameter("id_encargo"));
                request.setAttribute("f", true);
                check1 = true;
                check2 = false;
                check3 = false;
                check4 = false;
                check5 = false;
                check6 = false;
                request.setAttribute("a", false);
                request.setAttribute("b", false);
                request.setAttribute("c", false);
                request.setAttribute("d", false);
                request.setAttribute("es", false);
                request.setAttribute("confirmar", "disabled");

            } else {

                f = false;
                request.setAttribute("lista", false);
                request.getSession().setAttribute("accion", "Registrar Encargo");
                request.setAttribute("f", false);
                check1 = true;
                check2 = false;
                check3 = false;
                check4 = false;
                check5 = false;
                check6 = false;
                request.setAttribute("a", false);
                request.setAttribute("b", false);
                request.setAttribute("c", false);
                request.setAttribute("d", false);
                request.setAttribute("es", false);
                request.setAttribute("confirmar", "disabled");

            }

            request.setAttribute("co", false);
            request.setAttribute("listadoProductos", gp.obtenerProductos());
            request.setAttribute("listadoProveedores", gpr.obtenerProveedores());
            request.setAttribute("listadoMarcas", gm.obtenerMarcas());
            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/AM_Encargo.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);

                if (request.getParameter("id_e") != null) {

                    request.getSession().setAttribute("c", 1);
                    request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id_e")));
                    request.getSession().setAttribute("nombre", " el encargo " + ge.obtenerEncargo(Integer.parseInt(request.getParameter("id_e"))).getId_encargo());

                } else {

                    request.getSession().setAttribute("c", 2);
                    request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                    request.getSession().setAttribute("nombre", "el detalle = " + gde.obtenerDetalleEncargo(Integer.parseInt(request.getParameter("id"))).getId_detalle_encargo());

                }

            } else if (request.getParameter("e") != null) {

                if (request.getParameter("c").equals("1")) {

                    ge.eliminarEncargo(Integer.parseInt(request.getParameter("id")));

                } else {

                    gde.eliminarDetalleEncargo(Integer.parseInt(request.getParameter("id")));

                }

                request.getSession().setAttribute("e", false);

            } else {

                request.getSession().setAttribute("e", false);

            }

            if (!f) {

                request.setAttribute("listadoEncargos", ge.obtenerEncargosDTO());
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
                rd.forward(request, response);

            } else {

                request.setAttribute("lista", true);
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosIdDTO(Integer.parseInt(request.getParameter("id_encargo"))));
                request.setAttribute("co", false);
                request.setAttribute("listadoProductos", gp.obtenerProductos());
                request.setAttribute("listadoProveedores", gpr.obtenerProveedores());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.setAttribute("listadoCategorias", gc.obtenerCategorias());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/AM_Encargo.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("limite")) {

            if (ge.obtenerEncargos().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoEncargos", ge.obtenerEncargosDTO());
        request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("txtIdEncargo") != null) {
            request.setAttribute("f", true);
            request.setAttribute("id", request.getParameter("txtIdEncargo"));
        }

        if (f) {

            request.setAttribute("lista", true);
            request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosIdDTO(Integer.parseInt(request.getParameter("txtIdEncargo"))));

        }

        if (check1 && !request.getParameter("cmbCategorias").equals("Seleccionar...")) {

            request.setAttribute("btn1", "");
            request.setAttribute("confirmar", "disabled");
            request.setAttribute("a", true);
            request.setAttribute("op1", "disabled");
            request.setAttribute("listadoCategorias", gc.obtenerCategoriasID(Integer.parseInt(request.getParameter("cmbCategorias"))));
            request.setAttribute("listadoMarcas", gm.obtenerMarcasFiltro(Integer.parseInt(request.getParameter("cmbCategorias"))));

            if (check2 && !request.getParameter("cmbMarcas").equals("Seleccionar...")) {

                request.setAttribute("b", true);
                request.setAttribute("op2", "disabled");
                request.setAttribute("listadoMarcas", gm.obtenerMarcasID(Integer.parseInt(request.getParameter("cmbMarcas"))));
                request.setAttribute("listadoProductos", gp.obtenerProductosMarcaDTO(Integer.parseInt(request.getParameter("cmbMarcas"))));

                if (check3 && !request.getParameter("cmbProductos").equals("Seleccionar...")) {

                    request.setAttribute("c", true);
                    request.setAttribute("op3", "disabled");
                    request.setAttribute("listadoProductos", gp.obtenerProductosID(Integer.parseInt(request.getParameter("cmbProductos"))));
                    request.setAttribute("listadoProveedores", gpr.obtenerProveedoresFiltro(Integer.parseInt(request.getParameter("cmbMarcas"))));

                    if (check4 && !request.getParameter("cmbProveedores").equals("Seleccionar...")) {

                        request.setAttribute("d", true);
                        request.setAttribute("op4", "disabled");
                        request.setAttribute("btn1", "none");
                        request.setAttribute("confirmar", "enabled");
                        request.setAttribute("listadoProveedores", gpr.obtenerProveedoresID(Integer.parseInt(request.getParameter("cmbProveedores"))));
                        check5 = true;

                    }

                    check4 = true;

                }

                check3 = true;

            }

            check2 = true;

        } else {

            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            request.setAttribute("btn1", "Selecionar");
            request.setAttribute("confirmar", "disabled");

        }

        if (check5) {

            if (check6) {

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

                } else if (f) {

                    de.setId_encargo(e.getId_encargo());
                    gde.agregarDetalleEncargo(de);

                } else {

                    e.setId_estado(Integer.parseInt(request.getParameter("cmbEstados")));
                    de.setId_encargo(e.getId_encargo());
                    ge.actualizarEncargo(e);
                    gde.actualizarDetalleEncargo(de);

                }

                check1 = false;
                check2 = false;
                check3 = false;
                check4 = false;
                check5 = false;
                check6 = false;
                request.setAttribute("a", false);
                request.setAttribute("b", false);
                request.setAttribute("c", false);
                request.setAttribute("d", false);
                request.setAttribute("e", false);
                request.setAttribute("op1", "enabled");
                request.setAttribute("op2", "enabled");
                request.setAttribute("op3", "enabled");
                request.setAttribute("op4", "enabled");
                request.setAttribute("confirmar", "disabled");
                request.setAttribute("listadoCategorias", gc.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.setAttribute("listadoProductos", gp.obtenerProductos());
                request.setAttribute("listadoProveedores", gpr.obtenerProveedores());
                request.getSession().setAttribute("accion", "Agregar Detalle");
                request.setAttribute("id", request.getParameter("txtIdEncargo"));
                request.setAttribute("b", true);
                request.setAttribute("co", true);
                request.setAttribute("btn1", "");
                request.setAttribute("lista", true);
                request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosIdDTO(Integer.parseInt(request.getParameter("txtIdEncargo"))));

                if (!f) {

                    request.setAttribute("listadoEncargos", ge.obtenerEncargosDTO());
                    request.setAttribute("listadoDetalles", gde.obtenerDetalleEncargosDTO());
                    request.getSession().setAttribute("t", true);
                    request.getSession().setAttribute("co", true);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/listado_Encargos.jsp");
                    rd.forward(request, response);

                }

                this.f = true;

            }

            check6 = true;

        }
       
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Encargos/AM_Encargo.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
