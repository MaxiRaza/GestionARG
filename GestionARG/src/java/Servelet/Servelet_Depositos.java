package Servelet;

import Gestor.Gestor_Depositos;
import Gestor.Gestor_Sucursales;
import Modelo.Deposito;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Depositos", urlPatterns = {"/Depositos"})
public class Servelet_Depositos extends HttpServlet {

    Gestor_Depositos gd = new Gestor_Depositos();
    Gestor_Sucursales gs = new Gestor_Sucursales();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Variable global para asignar cantidad de filas de la tabla (Arranca por la fila N = 0)
        int filas = 9;
        request.getSession().setAttribute("servelet", "Depositos");
        String modo = request.getParameter("modo");
        request.getSession().setAttribute("modificar", false);
        request.getSession().setAttribute("accion", "Registrar");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 15);
                request.getSession().setAttribute("cantidad", filas);
                request.getSession().setAttribute("db", "disabled");
                if (gd.obtenerDepositosDTO().size() > filas) {
                    request.getSession().setAttribute("da", "enabled");
                } else {
                    request.getSession().setAttribute("da", "disabled");
                }
                request.setAttribute("listadoDepositos", gd.obtenerDepositosDTO());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Depositos/listado_Depositos.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("AM")) {

            if (request.getParameter("id_deposito") != null) {

                request.getSession().setAttribute("modificar", true);
                request.getSession().setAttribute("accion", "Editar");
                int id_deposito = Integer.parseInt(request.getParameter("id_deposito"));
                Deposito d = gd.obtenerDeposito(id_deposito);
                request.setAttribute("deposito", d);

            }

            request.setAttribute("listadoSucursales", gs.obtenerSucursales());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Depositos/AM_Deposito.jsp");
            rd.forward(request, response);

        } else if (modo.equals("eliminar")) {

            if (request.getParameter("a") != null) {

                request.getSession().setAttribute("e", true);
                request.getSession().setAttribute("id", Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("nombre", "el depósito " + gd.obtenerDeposito(Integer.parseInt(request.getParameter("id"))).getUbicacion());

            } else if (request.getParameter("e") != null) {

                request.getSession().setAttribute("e", false);
                gd.eliminarDeposito(Integer.parseInt(request.getParameter("id")));

            } else {

                request.getSession().setAttribute("e", false);

            }

            request.setAttribute("listadoDepositos", gd.obtenerDepositosDTO());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Depositos/listado_Depositos.jsp");
            rd.forward(request, response);

        } else if (modo.equals("limite")) {

            if (gd.obtenerDepositosDTO().size() > Integer.parseInt(request.getParameter("cantidad")) && Integer.parseInt(request.getParameter("cantidad")) > filas) {

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
        request.setAttribute("listadoDepositos", gd.obtenerDepositosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Depositos/listado_Depositos.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Deposito d = new Deposito();

        d.setId_deposito(Integer.parseInt(request.getParameter("txtIdRol")));
        d.setUbicacion(request.getParameter("txtUbicacion"));
        d.setId_sucursal(Integer.parseInt(request.getParameter("cmbSucursales")));

        if (d.getId_deposito() == 0) {
            gd.agregarDeposito(d);
        } else {
            gd.actualizarDeposito(d);
        }

        request.setAttribute("listadoDepositos", gd.obtenerDepositosDTO());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Depositos/listado_Depositos.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
