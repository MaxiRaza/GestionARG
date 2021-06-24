package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Detalle_Facturas;
import Gestor.Gestor_Facturas;
import Gestor.Gestor_Formas_de_Pagos;
import Gestor.Gestor_Marcas;
import Gestor.Gestor_Productos;
import Gestor.Gestor_Sucursales;
import Gestor.Gestor_Usuarios;
import Modelo.Carrito;
import Modelo.Detalle_Factura;
import Modelo.Factura;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Compras", urlPatterns = {"/Compras"})
public class Servelet_Compras extends HttpServlet {

    Gestor_Facturas gf = new Gestor_Facturas();
    Gestor_Detalle_Facturas gdf = new Gestor_Detalle_Facturas();
    Gestor_Productos gp = new Gestor_Productos();
    Gestor_Categorias ga = new Gestor_Categorias();
    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Usuarios gu = new Gestor_Usuarios();
    Gestor_Sucursales gs = new Gestor_Sucursales();
    Gestor_Formas_de_Pagos gfdp = new Gestor_Formas_de_Pagos();
    int id = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 7;
        request.getSession().setAttribute("servelet", "Compras");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");
        request.getSession().setAttribute("a", false);
        request.getSession().setAttribute("t", false);

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
                request.setAttribute("listadoCarrito", gf.obtenerCarritoDTO());
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

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("m", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("ND") != null) {

                request.getSession().setAttribute("m", false);
                Carrito c = new Carrito();
                c.setId_producto(gp.obtenerProducto(Integer.parseInt(request.getParameter("id"))).getId_producto());
                c.setNombre(gp.obtenerProducto(Integer.parseInt(request.getParameter("id"))).getNombre());
                c.setCantidad(1);
                gf.agregarDetalleCarrito(c);

            } else if (request.getParameter("BD") != null) {

                gf.eliminarDetalleCarrito(Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("v") != null) {

                gf.eliminarCarrito();

            } else {

                request.getSession().setAttribute("m", false);

            }

        } else if (modo.equals("comprar")) {

            if (request.getParameter("BD") != null) {

                gf.eliminarDetalleCarrito(Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("C") != null) {
                
                for (Carrito d : gf.obtenerCarritoDTO()) {

                Carrito c = new Carrito();
                c.setId_compra(d.getId_compra());
                c.setCantidad(Integer.parseInt(request.getParameter("txtCantidad" + d.getId_compra())));
                gf.actualizarDetalleCarrito(c);

            }
                
            }

            if (!gf.obtenerCarrito().isEmpty()) {

                float total = 0;
                for (Carrito d : gf.obtenerCarritoDTO()) {
                    total += d.getCantidad() * d.getPrecio();
                }
                request.setAttribute("total", total);
                id = Integer.parseInt(request.getParameter("usuario"));
                request.setAttribute("listadoSucursales", gs.obtenerSucursales());
                request.setAttribute("listadoFormasdePago", gfdp.obtenerFormasDePagos());
                request.setAttribute("listadoCarrito", gf.obtenerCarritoDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/confirmar_Compra.jsp");
                rd.forward(request, response);

            }

        }

        request.getSession().setAttribute("n", filas);
        if (request.getParameter("cantidad") != null) {
            request.getSession().setAttribute("cantidad", (Integer.parseInt(request.getParameter("cantidad"))));
        }
        request.setAttribute("listadoCarrito", gf.obtenerCarritoDTO());
        request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
        request.setAttribute("listadoCategorias", ga.obtenerCategorias());
        request.setAttribute("listadoMarcas", gm.obtenerMarcas());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("cmbFormasdePago") == null) {

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

        } else {

            Factura f = new Factura();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy HH:mm:ss");

            f.setFecha(sdf.format(new Date()));
            f.setDescuento(0);
            f.setId_sucursal(Integer.parseInt(request.getParameter("cmbSucursales")));
            f.setId_usuario(id);
            f.setId_forma_de_pago(Integer.parseInt(request.getParameter("cmbFormasdePago")));
            f.setId_cliente(1);
            gf.agregarFactura(f);

            for (Carrito d : gf.obtenerCarritoDTO()) {

                Detalle_Factura df = new Detalle_Factura();

                df.setId_factura(gf.obtenerUltimoIdFactura());
                df.setCantidad(Integer.parseInt(request.getParameter("txtCantidad" + d.getId_compra())));
                df.setImporte(d.getPrecio());
                df.setId_producto(d.getId_producto());
                gdf.agregarDetalleFactura(df);

            }

            gf.eliminarCarrito();
            request.setAttribute("listadoProductos", gp.obtenerProductosDTO());
            request.setAttribute("listadoCategorias", ga.obtenerCategorias());
            request.setAttribute("listadoMarcas", gm.obtenerMarcas());
            request.getSession().setAttribute("id_categoria", 0);
            request.getSession().setAttribute("id_marca", 0);
            request.setAttribute("a", true);

        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Ventas/listado_articulos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
