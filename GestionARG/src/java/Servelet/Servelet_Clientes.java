package Servelet;

import Gestor.Gestor_Clientes;
import Gestor.Gestor_Contactos;
import Gestor.Gestor_Tipo_Clientes;
import Modelo.Cliente;
import Modelo.Contacto;
import Modelo.DTO.DTO_Cliente;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Clientes", urlPatterns = {"/Clientes"})
public class Servelet_Clientes extends HttpServlet {

    Gestor_Clientes gc = new Gestor_Clientes();
    Gestor_Contactos gco = new Gestor_Contactos();
    Gestor_Tipo_Clientes gtc = new Gestor_Tipo_Clientes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Clientes");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 4);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gc.obtenerClientesDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoClientes", gc.obtenerClientesDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/listado_Clientes.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_cliente") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
                DTO_Cliente c = gc.obtenerClienteDTO(id_cliente);
                request.setAttribute("cliente", c);

            }

            request.setAttribute("listadoTipos", gtc.obtenerTipoClientes());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/AM_Cliente.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", "el cliente " + (gc.obtenerCliente(Integer.parseInt(request.getParameter("id"))).getNombre() + " " + gc.obtenerCliente(Integer.parseInt(request.getParameter("id"))).getApellido()));

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gc.eliminarCliente(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

        } else if (modo.equals("limite")) {

            if (gc.obtenerClientesDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoClientes", gc.obtenerClientesDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/listado_Clientes.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente c = new Cliente();
        Contacto co = new Contacto();

        c.setId_cliente(Integer.parseInt(request.getParameter("txtIdCliente")));
        c.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        co.setId_contacto(Integer.parseInt(request.getParameter("txtIdContacto")));
        c.setNombre(request.getParameter("txtNombre"));
        c.setApellido(request.getParameter("txtApellido"));
        c.setDocumento(request.getParameter("txtDocumento"));
        c.setFecha_nac(request.getParameter("txtFechaNac"));
        c.setDireccion(request.getParameter("txtDireccion"));
        co.setCorreo(request.getParameter("txtCorreo"));
        co.setTelefono(request.getParameter("txtTelefono"));
        c.setId_tipo_cliente(Integer.parseInt(request.getParameter("cmbTipos")));

        if (c.getId_cliente() == 0) {
            if (gco.obtenerIdContacto(co.getCorreo(), co.getTelefono()) == 0) {
                gco.agregarContacto(co);
            }
            c.setId_contacto(gco.obtenerIdContacto(co.getCorreo(), co.getTelefono()));
            gc.agregarCliente(c);
        } else {
            gco.actualizarContacto(co);
            gc.actualizarCliente(c);
        }

        request.setAttribute("listadoClientes", gc.obtenerClientesDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Clientes/listado_Clientes.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
