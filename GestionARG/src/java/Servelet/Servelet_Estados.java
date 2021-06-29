package Servelet;

import Gestor.Gestor_Estados;
import Modelo.Estado;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Estados", urlPatterns = {"/Estados"})
public class Servelet_Estados extends HttpServlet {
    
    Gestor_Estados ge = new Gestor_Estados();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Estados");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");
        request.getSession().setAttribute("e", false);
        request.getSession().setAttribute("t", true);
        request.getSession().setAttribute("co", false);

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 20);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (ge.obtenerEstados().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoEstados", ge.obtenerEstados());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Estados/listado_Estados.jsp");
        rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {
            
            request.getSession().setAttribute("t", false);

            if (request.getParameter("id_estado") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_estado = Integer.parseInt(request.getParameter("id_estado"));
                Estado e = ge.obtenerEstado(id_estado);
                request.setAttribute("estado", e);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Estados/AM_Estado.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el rol " + ge.obtenerEstado(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                ge.eliminarEstado(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoEstados", ge.obtenerEstados());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Estados/listado_Estados.jsp");
        rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (ge.obtenerEstados().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoEstados", ge.obtenerEstados());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Estados/listado_Estados.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Estado e = new Estado();

        e.setId_estado(Integer.parseInt(request.getParameter("txtIdEstado")));
        e.setNombre(request.getParameter("txtNombre"));

        if (e.getId_estado()== 0) {
            ge.agregarEstado(e);
        } else {
            ge.actualizarEstado(e);
        }

        request.getSession().setAttribute("co", true);
        request.getSession().setAttribute("t", true);
        request.setAttribute("listadoEstados", ge.obtenerEstados());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Estados/listado_Estados.jsp");
        rd.forward(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }
}
