package Servelet;

import Gestor.Gestor_Formas_de_Pagos;
import Modelo.Forma_de_Pago;
import java.io.IOException;
import java.io.PrintWriter;
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

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("admin") != null) {

                request.getSession().setAttribute("activar", 14);
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

            int id_forma_de_pago = Integer.parseInt(request.getParameter("id_forma_de_pago"));
            gfp.eliminarFormaDePago(id_forma_de_pago);
            request.setAttribute("listadoFormasDePago", gfp.obtenerFormasDePagos());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Formas_de_Pagos/listado_Formas_de_Pagos.jsp");
            rd.forward(request, response);

        }

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
