package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Depositos;
import Gestor.Gestor_Facturas;
import Gestor.Gestor_Marcas;
import Gestor.Gestor_Productos;
import Modelo.Producto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Compras", urlPatterns = {"/Compras"})
public class Servelet_Compras extends HttpServlet {

    Gestor_Facturas gf = new Gestor_Facturas();
    Gestor_Productos gp = new Gestor_Productos();
    Gestor_Categorias ga = new Gestor_Categorias();
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Depositos gd = new Gestor_Depositos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 7;
        request.getSession().setAttribute("servelet", "Compras");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 9);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gp.obtenerProductosDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
                request.setAttribute("listadoCategorias", ga.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", 0);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("Limpiar")) {

            request.getSession().setAttribute("id_categoria", 0);
            request.getSession().setAttribute("id_marca", 0);

        } else if (modo.equals("limite")) {

            if (gp.obtenerProductosMarcaDTO(filas).size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "enable");
                request.getSession().setAttribute("db", "enabled");

            } else if (Integer.parseInt(request.getParameter("cantidad")) > filas) {

                request.getSession().setAttribute("da", "disabled");
                request.getSession().setAttribute("db", "enable");

            } else {

                request.getSession().setAttribute("da", "enabled");
                request.getSession().setAttribute("db", "disabled");

            }

        } else if (modo.equals("carrito")) {
            ArrayList<Producto> lista = new ArrayList<>();
            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("m", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("c") != null) {

                request.getSession().setAttribute("m", false);
                lista.add(gp.obtenerProducto(Integer.parseInt(request.getParameter("id"))));
                request.setAttribute("listadoCarrito", lista);

            } else if (request.getParameter("b") != null) {
                
                lista.remove(Integer.parseInt(request.getParameter("id")));
                 request.setAttribute("listadoCarrito", lista);
                
            } else {

                request.getSession().setAttribute("m", false);

            }

        }

        request.getSession().setAttribute("n", filas);
        if (request.getParameter("cantidad") != null) {
            request.getSession().setAttribute("cantidad", (Integer.parseInt(request.getParameter("cantidad"))));
        }
        request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
        request.setAttribute("listadoCategorias", ga.obtenerCategorias());
        request.setAttribute("listadoMarcas", gm.obtenerMarcas());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (true) {

            request.setAttribute("listadoCategorias", ga.obtenerCategorias());

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
                request.setAttribute("listadoCategorias", ga.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", 0);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
            rd.forward(request, response);
            return;

        }

//        Factura f = new Factura();
    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
