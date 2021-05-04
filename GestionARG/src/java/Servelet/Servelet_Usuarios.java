package Servelet;

import Gestor.Gestor_Contactos;
import Gestor.Gestor_Usuarios;
import Modelo.Contacto;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Usuario u = new Usuario();
        Contacto c = new Contacto();

        u.setId_usuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        u.setNombre(request.getParameter("txtNombre"));
        u.setApellido(request.getParameter("txtApellido"));
        u.setDocumento(Double.parseDouble(request.getParameter("txtDocumento")));
        u.setFecha_nac(request.getParameter("txtFechaNac"));
        u.setDireccion(request.getParameter("txtDireccion"));
        c.setCorreo(request.getParameter("txtCorreo"));
        c.setTelefono(request.getParameter("txtTelefono"));
        u.setAlias(request.getParameter("txtAlias"));
        u.setContrasenia(request.getParameter("txtContrasenia"));

        if (u.getId_usuario() == 0) {
            if (c.getCorreo() != null || c.getTelefono() != null) {
                gc.agregarContacto(c);
                u.setId_contacto(gc.obtenerIdContacto(c.getCorreo(), c.getTelefono()));
            }
            gu.agregarUsuario(u);
        } else {
            gu.actualizarUsuario(u);
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GestionARG ";
    }

}
