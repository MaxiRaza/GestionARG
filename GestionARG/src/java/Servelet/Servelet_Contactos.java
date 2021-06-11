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
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Contactos");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 18);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gc.obtenerContactos().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
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

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", "el contacto " + Integer.parseInt(request.getParameter("id")));

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gc.eliminarContacto(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

        } else if (modo.equals("limite")) {

            if (gc.obtenerContactos().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoContactos", gc.obtenerContactos());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Contactos/listado_Contactos.jsp");
        rd.forward(request, response);

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
