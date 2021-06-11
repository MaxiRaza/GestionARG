package Servelet;

import Gestor.Gestor_Formas_de_Pagos;
import Modelo.Forma_de_Pago;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Formas_de_Pagos", urlPatterns = {"/Formas_de_Pagos"})
public class Servelet_Formas_de_Pagos extends HttpServlet {

    Gestor_Formas_de_Pagos gfp = new Gestor_Formas_de_Pagos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Formas_de_Pagos");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 14);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gfp.obtenerFormasDePagos().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoFormasDePago", gfp.obtenerFormasDePagos());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Formas_de_Pagos/listado_Formas_de_Pagos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_forma_de_pago") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_forma_de_pago = Integer.parseInt(request.getParameter("id_forma_de_pago"));
                Forma_de_Pago fp = gfp.obtenerFormaDePago(id_forma_de_pago);
                request.setAttribute("forma_de_pago", fp);

            }

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Formas_de_Pagos/AM_Forma_de_Pago.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", "la forma de pago " + gfp.obtenerFormaDePago(Integer.parseInt(request.getParameter("id"))).getNombre());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gfp.eliminarFormaDePago(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

        } else if (modo.equals("limite")) {

            if (gfp.obtenerFormasDePagos().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoFormasDePago", gfp.obtenerFormasDePagos());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Formas_de_Pagos/listado_Formas_de_Pagos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Forma_de_Pago fp = new Forma_de_Pago();

        fp.setId_forma_de_pago(Integer.parseInt(request.getParameter("txtIdFormaDePago")));
        fp.setNombre(request.getParameter("txtNombre"));

        if (fp.getId_forma_de_pago() == 0) {
            gfp.agregarFormaDePago(fp);
        } else {
            gfp.actualizarFormaDePago(fp);
        }

        request.setAttribute("listadoFormasDePago", gfp.obtenerFormasDePagos());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Formas_de_Pagos/listado_Formas_de_Pagos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
