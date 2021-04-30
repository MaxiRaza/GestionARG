package Servelet;

import Gestor.Gestor_Categorias;
import Modelo.Categoria;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Categorias", urlPatterns = {"/Categorias"})
public class Servelet_Categorias extends HttpServlet {

    Gestor_Categorias gc = new Gestor_Categorias();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Categoria c = new Categoria();

    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
