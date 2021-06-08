package Servelet;

import Gestor.Gestor_Categorias;
import Modelo.Categoria;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Categorias", urlPatterns = {"/Categorias"})
public class Servelet_Categorias extends HttpServlet {

    Gestor_Categorias gc = new Gestor_Categorias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 17);
                request.setAttribute("listadoCategorias", gc.obtenerCategorias());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Categorias/listado_Categorias.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_categoria") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
                Categoria c = gc.obtenerCategoria(id_categoria);
                request.setAttribute("categoria", c);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Categorias/AM_Categoria.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
            gc.eliminarCategoria(id_categoria);
            request.setAttribute("listadoCategorias", gc.obtenerCategorias());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Categorias/listado_Categorias.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Categoria c = new Categoria();

        c.setId_categoria(Integer.parseInt(request.getParameter("txtIdCategoria")));
        c.setNombre(request.getParameter("txtNombre"));

        if (c.getId_categoria()== 0) {
            gc.agregarCategorias(c);
        } else {
            gc.actualizarCategoria(c);
        }

        request.setAttribute("listadoCategorias", gc.obtenerCategorias());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Categorias/listado_Categorias.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
