package Servelet;

import Gestor.Gestor_Clientes;
import Modelo.Cliente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Clientes", urlPatterns = {"/Clientes"})
public class Servelet_Clientes extends HttpServlet {

    Gestor_Clientes gc = new Gestor_Clientes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cliente c = new Cliente();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
