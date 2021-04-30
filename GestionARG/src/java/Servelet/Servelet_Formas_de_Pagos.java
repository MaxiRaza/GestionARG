package Servelet;

import Gestor.Gestor_Formas_de_Pagos;
import Modelo.Forma_de_Pago;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Formas_de_Pagos", urlPatterns = {"/Formas_de_Pagos"})
public class Servelet_Formas_de_Pagos extends HttpServlet {

    Gestor_Formas_de_Pagos gfdp = new Gestor_Formas_de_Pagos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Forma_de_Pago fdp = new Forma_de_Pago();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
