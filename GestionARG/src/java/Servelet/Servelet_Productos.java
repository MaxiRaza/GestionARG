package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Depositos;
import Gestor.Gestor_Marcas;
import Gestor.Gestor_Productos;
import Modelo.DTO.DTO_Producto;
import Modelo.Producto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Productos", urlPatterns = {"/Productos"})
public class Servelet_Productos extends HttpServlet {

    Gestor_Productos gp = new Gestor_Productos();
    Gestor_Categorias gc = new Gestor_Categorias();
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Depositos gd = new Gestor_Depositos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Productos");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");
        request.getSession().setAttribute("t", true);

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 2);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gp.obtenerProductosDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoCategorias", gc.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", 0);
                request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            request.getSession().setAttribute("t", false);

            if (request.getParameter("id_producto") != null) {

                try {
                    if (request.getParameter("accion").equals("Registrar") == false) {
                        request.getSession().setAttribute("modificar", true);
                        request.getSession().setAttribute("accion", "Editar");
                    }
                } catch (Exception e) {
                    request.getSession().setAttribute("modificar", true);
                    request.getSession().setAttribute("accion", "Editar");
                }

                DTO_Producto p = gp.obtenerProductoDTO(Integer.parseInt(request.getParameter("id_producto")));
                request.setAttribute("producto", p);

            }

            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/FiltroProductos.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el producto " + gp.obtenerProducto(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gp.eliminarProducto(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gp.obtenerProductosDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "enable");
                request.getSession().setAttribute("db", "enabled");

            } else if (Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "disabled");
                request.getSession().setAttribute("db", "enable");

            } else {

                request.getSession().setAttribute("da", "enabled");
                request.getSession().setAttribute("db", "disabled");

            }

        } else if (modo.equals("Limpiar")) {

            request.getSession().setAttribute("id_categoria", 0);
            request.getSession().setAttribute("id_marca", 0);

        }

        request.getSession().setAttribute("n", filas);
        if (request.getParameter("cantidad") != null) {
            request.getSession().setAttribute("cantidad", (Integer.parseInt(request.getParameter("cantidad"))));
        }
        request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
        request.setAttribute("listadoCategorias", gc.obtenerCategorias());
        request.setAttribute("listadoMarcas", gm.obtenerMarcas());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("cmbCategoriass") == null) {

            request.setAttribute("listadoCategorias", gc.obtenerCategorias());

            if (Integer.parseInt(request.getParameter("cmbCategorias")) != 0 && Integer.parseInt(request.getParameter("cmbMarcas")) == 0) {

                int id_categoria = Integer.parseInt(request.getParameter("cmbCategorias"));
                request.getSession().setAttribute("id_marca", 0);
                request.getSession().setAttribute("id_categoria", id_categoria);
                request.setAttribute("listadoMarcas", gm.obtenerMarcasFiltro(id_categoria));
                request.setAttribute("listadoProductos", gp.obtenerProductosCategoriaDTO(id_categoria));

            } else if (Integer.parseInt(request.getParameter("cmbMarcas")) != 0 && Integer.parseInt(request.getParameter("cmbCategorias")) == 0) {

                int id_marca = Integer.parseInt(request.getParameter("cmbMarcas"));
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", id_marca);
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.setAttribute("listadoProductos", gp.obtenerProductosMarcaDTO(id_marca));

            } else if (Integer.parseInt(request.getParameter("cmbMarcas")) != 0 && Integer.parseInt(request.getParameter("cmbCategorias")) != 0) {

                int id_marca = Integer.parseInt(request.getParameter("cmbMarcas"));
                int id_categoria = gm.obtenerMarca(id_marca).getId_categoria();
                request.getSession().setAttribute("id_categoria", id_categoria);
                request.getSession().setAttribute("id_marca", id_marca);
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.setAttribute("listadoProductos", gp.obtenerProductosMarcaDTO(id_marca));

            } else {

                request.getSession().setAttribute("activar", 9);
                request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
                request.setAttribute("listadoCategorias", gc.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", 0);

            }

        } else {

            if (request.getParameter("cmbCategoriass") != null) {

                if (request.getParameter("txtIdProducto") != null && !request.getParameter("txtIdProducto").equals("0")) {

                    request.getSession().setAttribute("modificar", true);
                    request.getSession().setAttribute("accion", "Editar");
                    DTO_Producto p = gp.obtenerProductoDTO(Integer.parseInt(request.getParameter("txtIdProducto")));
                    request.setAttribute("producto", p);

                }

                request.setAttribute("listadoMarcas", gm.obtenerMarcasFiltro(Integer.parseInt(request.getParameter("cmbCategoriass"))));
                request.setAttribute("listadoDepositos", gd.obtenerDepositos());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/AM_Producto.jsp");
                rd.forward(request, response);
                return;

            }

            Producto p = new Producto();

            p.setId_producto(Integer.parseInt(request.getParameter("txtIdProducto")));
            p.setCodigo(request.getParameter("txtCodigo"));
            p.setNombre(request.getParameter("txtNombre"));
            p.setFecha_fab(request.getParameter("txtFechaElaboracion"));
            p.setFecha_ven(request.getParameter("txtFechaVencimiento"));
            p.setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
            p.setDescripcion(request.getParameter("txtDescripcion"));
            p.setStock(Float.parseFloat(request.getParameter("txtCantidad")));
            p.setId_marca(Integer.parseInt(request.getParameter("cmbMarcas")));
            p.setId_deposito(Integer.parseInt(request.getParameter("cmbDepositos")));

            if (p.getId_producto() == 0) {
                gp.agregarProducto(p);
            } else {
                gp.actualizarProducto(p);
            }

            request.setAttribute("listadoProductos", gp.obtenerProductosDTO());

        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
