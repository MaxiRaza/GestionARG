package Servelet;

import Gestor.Gestor_Roles;
import Modelo.Rol;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Roles", urlPatterns = {"/Roles"})
public class Servelet_Roles extends HttpServlet {

    Gestor_Roles gr = new Gestor_Roles();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Roles");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");
        request.getSession().setAttribute("e", false);
        request.getSession().setAttribute("t", true);

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 12);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gr.obtenerRoles().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoRoles", gr.obtenerRoles());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {
            
            request.getSession().setAttribute("t", false);

            if (request.getParameter("id_rol") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_rol = Integer.parseInt(request.getParameter("id_rol"));
                Rol r = gr.obtenerRol(id_rol);
                request.setAttribute("roll", r);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/AM_Rol.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el rol " + gr.obtenerRol(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gr.eliminarRol(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoRoles", gr.obtenerRoles());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gr.obtenerRoles().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoRoles", gr.obtenerRoles());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Rol r = new Rol();

        r.setId_rol(Integer.parseInt(request.getParameter("txtIdRol")));
        r.setNombre(request.getParameter("txtNombre"));

        if (r.getId_rol() == 0) {
            gr.agregarRol(r);
        } else {
            gr.actualizarRol(r);
        }

        request.setAttribute("listadoRoles", gr.obtenerRoles());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
