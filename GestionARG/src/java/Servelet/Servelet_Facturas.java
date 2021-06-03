package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Depositos;
import Gestor.Gestor_Facturas;
import Gestor.Gestor_Marcas;
import Gestor.Gestor_Productos;
import Modelo.DTO.DTO_Producto;
import Modelo.Factura;
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
    Gestor_Productos gp = new Gestor_Productos();
    Gestor_Categorias ga = new Gestor_Categorias();
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Depositos gd = new Gestor_Depositos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 9);
                request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
                request.setAttribute("listadoCategorias", ga.obtenerCategorias());
                request.setAttribute("listadoMarcas", gm.obtenerMarcas());
                request.getSession().setAttribute("id_categoria", 0);
                request.getSession().setAttribute("id_marca", 0);
                request.getSession().setAttribute("cantidad", 7);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }

        } else if (modo.equals("Limpiar")) {

            request.getSession().setAttribute("id_categoria", 0);
            request.getSession().setAttribute("id_marca", 0);

        } else if (modo.equals("sumar")) {
            
            request.getSession().setAttribute("cantidad", request.getParameter("cantidad"));
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

        Factura f = new Factura();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
