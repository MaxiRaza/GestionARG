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
        
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 12);
                request.setAttribute("listadoRoles", gr.obtenerRoles());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_rol") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_rol = Integer.parseInt(request.getParameter("id_rol"));
                Rol r = gr.obtenerRol(id_rol);
                request.setAttribute("rol", r);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/AM_Rol.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_rol = Integer.parseInt(request.getParameter("id_rol"));
            gr.eliminarRol(id_rol);
            request.setAttribute("listadoRoles", gr.obtenerRoles());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Roles/listado_Roles.jsp");
            rd.forward(request, response);

        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Rol r = new Rol();

        r.setId_rol(Integer.parseInt(request.getParameter("txtIdRol")));
        r.setNombre(request.getParameter("txtNombre"));

        if (r.getId_rol()== 0) {
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
