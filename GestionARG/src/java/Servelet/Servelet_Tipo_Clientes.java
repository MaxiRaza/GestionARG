package Servelet;

import Gestor.Gestor_Tipo_Clientes;
import Modelo.Tipo_Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Tipo_Clientes", urlPatterns = {"/Tipo_Clientes"})
public class Servelet_Tipo_Clientes extends HttpServlet {

    Gestor_Tipo_Clientes gtp = new Gestor_Tipo_Clientes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Tipo_Cliente tp = new Tipo_Cliente();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
