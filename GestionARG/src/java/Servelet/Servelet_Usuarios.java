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

        String modo = request.getParameter("modo");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 8);
                request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listadoUsuarios.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }
        } else if (modo.equals("registrar")) {

            request.setAttribute("listadoRoles", gr.obtenerRoles());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/registrarUsuario.jsp");
            rd.forward(request, response);

        } else if (modo.equals("modificar")) {

            request.setAttribute("listadoRoles", gr.obtenerRoles());
            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            DTO_Usuario u = gu.obtenerUsuarioDTO(id_usuario);
            request.setAttribute("usuario", u);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/modificarUsuario.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
            gu.eliminarUsuario(id_usuario);
            request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listadoUsuarios.jsp");
            rd.forward(request, response);

        }

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

        request.setAttribute("listadoUsuarios", gu.obtenerUsuariosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Usuarios/listadoUsuarios.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG ";
    }

}
