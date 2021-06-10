package Servelet;

import Gestor.Gestor_Tipo_Clientes;
import Modelo.Tipo_Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Tipo_Clientes", urlPatterns = {"/Tipo_Clientes"})
public class Servelet_Tipo_Clientes extends HttpServlet {

    Gestor_Tipo_Clientes gtc = new Gestor_Tipo_Clientes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 10);
                request.setAttribute("listadoTiposClientes", gtc.obtenerTipoClientes());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Clientes/listado_Tipo_Clientes.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_tipo_cliente") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_tipo_cliente = Integer.parseInt(request.getParameter("id_tipo_cliente"));
                Tipo_Cliente tc = gtc.obtenerTipoCliente(id_tipo_cliente);
                request.setAttribute("tipo_cliente", tc);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Clientes/AM_Tipo_Cliente.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("servelet", "Tipo_Clientes");
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", " el tipo de cliente " +  gtc.obtenerTipoCliente(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gtc.eliminarTipoCliente(Integer.parseInt(request.getParameter("id")));

            } else {
                request.getSession().setAttribute("e", false);
            }

            request.setAttribute("listadoTiposClientes", gtc.obtenerTipoClientes());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Clientes/listado_Tipo_Clientes.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Tipo_Cliente tc = new Tipo_Cliente();

        tc.setId_tipo_cliente(Integer.parseInt(request.getParameter("txtIdTipoCliente")));
        tc.setNombre(request.getParameter("txtNombre"));

        if (tc.getId_tipo_cliente() == 0) {
            gtc.agregarTipoCliente(tc);
        } else {
            gtc.actualizarTipoCliente(tc);
        }

        request.setAttribute("listadoTiposClientes", gtc.obtenerTipoClientes());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Tipo_Clientes/listado_Tipo_Clientes.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
