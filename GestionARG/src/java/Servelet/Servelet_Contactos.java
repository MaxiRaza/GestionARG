package Servelet;

import Gestor.Gestor_Contactos;
import Modelo.Contacto;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Contactos", urlPatterns = {"/Contactos"})
public class Servelet_Contactos extends HttpServlet {

    Gestor_Contactos gc = new Gestor_Contactos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 18);
                request.setAttribute("listadoContactos", gc.obtenerContactos());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Contactos/listado_Contactos.jsp");
            rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_contacto") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_contacto = Integer.parseInt(request.getParameter("id_contacto"));
                Contacto c = gc.obtenerContacto(id_contacto);
                request.setAttribute("contacto", c);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Contactos/AM_Contacto.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_contacto = Integer.parseInt(request.getParameter("id_contacto"));
            gc.eliminarContacto(id_contacto);
            request.setAttribute("listadoContactos", gc.obtenerContactos());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Contactos/listado_Contactos.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Contacto c = new Contacto();

        c.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        c.setTelefono(request.getParameter("txtTelefono"));
        c.setCorreo(request.getParameter("txtCorreo"));

        if (c.getId_contacto() == 0) {
            gc.agregarContacto(c);
        } else {
            gc.actualizarContacto(c);
        }

        request.setAttribute("listadoContactos", gc.obtenerContactos());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Contactos/listado_Contactos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG ";
    }

}
