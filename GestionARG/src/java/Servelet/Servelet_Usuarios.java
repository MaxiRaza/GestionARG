package Servelet;

import Gestor.Gestor_Contactos;
import Gestor.Gestor_Roles;
import Gestor.Gestor_Usuarios;
import Modelo.Contacto;
import Modelo.DTO.DTO_Usuario;
import Modelo.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Usuarios", urlPatterns = {"/Usuarios"})
public class Servelet_Usuarios extends HttpServlet {

    Gestor_Usuarios gu = new Gestor_Usuarios();
    Gestor_Contactos gc = new Gestor_Contactos();
    Gestor_Roles gr = new Gestor_Roles();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Usuarios");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {
                request.getSession().setAttribute("activar", 8);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gu.obtenerUsuariosDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listado_Usuarios.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_usuario") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
                DTO_Usuario u = gu.obtenerUsuarioDTO(id_usuario);
                request.setAttribute("usuario", u);

            }

            request.setAttribute("listadoRoles", gr.obtenerRoles());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/AM_Usuario.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el usuario " + (gu.obtenerUsuario(Integer.parseInt(request.getParameter("id"))).getNombre() + " " + gu.obtenerUsuario(Integer.parseInt(request.getParameter("id"))).getApellido()));

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gu.eliminarUsuario(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

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
        request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listado_Usuarios.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario u = new Usuario();
        Contacto c = new Contacto();

        u.setId_usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        u.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        c.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        u.setNombre(request.getParameter("txtNombre"));
        u.setApellido(request.getParameter("txtApellido"));
        u.setDocumento(request.getParameter("txtDocumento"));
        u.setFecha_nac(request.getParameter("txtFechaNac"));
        u.setDireccion(request.getParameter("txtDireccion"));
        c.setCorreo(request.getParameter("txtCorreo"));
        c.setTelefono(request.getParameter("txtTelefono"));
        u.setAlias(request.getParameter("txtAlias"));
        u.setContrasenia(request.getParameter("txtContrasenia"));
        u.setId_rol(Integer.parseInt(request.getParameter("cmbRoles")));

        if (u.getId_usuario() == 0) {
            if (gc.obtenerIdContacto(c.getCorreo(), c.getTelefono()) == 0) {
                gc.agregarContacto(c);
            }
            u.setId_contacto(gc.obtenerIdContacto(c.getCorreo(), c.getTelefono()));
            gu.agregarUsuario(u);
        } else {
            gc.actualizarContacto(c);
            gu.actualizarUsuario(u);
        }

        if (request.getSession().getAttribute("admin") != null) {
            request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listado_Usuarios.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "GestionARG ";
    }

}
