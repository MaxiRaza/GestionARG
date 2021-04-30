package Servelet;

import Gestor.Gestor_Depositos;
import Modelo.Deposito;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Depositos", urlPatterns = {"/Depositos"})
public class Servelet_Depositos extends HttpServlet {

    Gestor_Depositos gd = new Gestor_Depositos();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Deposito d = new Deposito();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
