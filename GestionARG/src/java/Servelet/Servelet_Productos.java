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
    Gestor_Categorias ga = new Gestor_Categorias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {
                request.getSession().setAttribute("activar", 2);
                request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);
            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_producto") != null) {
                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                DTO_Producto p = gp.obtenerProductoDTO( Integer.parseInt(request.getParameter("id_producto")));
                request.setAttribute("producto", p);
            }

            request.setAttribute("listadoCategorias", ga.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/FiltroProductos.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_producto = Integer.parseInt(request.getParameter("id_producto"));
            gp.eliminarProducto(id_producto);
            request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("cmbCategorias") != null) {
            if (request.getParameter("txtIdProducto") != null && !request.getParameter("txtIdProducto").equals("0")) {
                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                DTO_Producto p = gp.obtenerProductoDTO(Integer.parseInt(request.getParameter("txtIdProducto")));
                request.setAttribute("producto", p);
            }
            request.setAttribute("listadoMarcas", gm.obtenerMarcasFiltro(Integer.parseInt(request.getParameter("cmbCategorias"))));
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
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Productos/listado_Productos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
