package Servelet;

import Gestor.Gestor_Categorias;
import Gestor.Gestor_Marcas;
import Modelo.Marca;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Marcas", urlPatterns = {"/Marcas"})
public class Servelet_Marcas extends HttpServlet {

    Gestor_Marcas gm = new Gestor_Marcas();
    Gestor_Categorias gc = new Gestor_Categorias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 4;
        request.getSession().setAttribute("servelet", "Marcas");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 13);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gm.obtenerMarcasDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoMarcas", gm.obtenerMarcasDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Marcas/listado_Marcas.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_marca") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_marca = Integer.parseInt(request.getParameter("id_marca"));
                Marca m = gm.obtenerMarca(id_marca);
                request.setAttribute("marca", m);

            }

            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Marcas/AM_Marca.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " la marca " + gm.obtenerMarca(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gm.eliminarMarca(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoMarcas", gm.obtenerMarcasDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Marcas/listado_Marcas.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gm.obtenerMarcasDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoMarcas", gm.obtenerMarcasDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Marcas/listado_Marcas.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Marca m = new Marca();

        m.setId_marca(Integer.parseInt(request.getParameter("txtIdRol")));
        m.setNombre(request.getParameter("txtNombre"));
        m.setId_categoria(Integer.parseInt(request.getParameter("cmbCategorias")));

        if (m.getId_marca() == 0) {
            gm.agregarMarca(m);
        } else {
            gm.actualizarMarca(m);
        }

        request.setAttribute("listadoMarcas", gm.obtenerMarcasDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Marcas/listado_Marcas.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
