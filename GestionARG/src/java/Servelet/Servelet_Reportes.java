package Servelet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Reportes", urlPatterns = {"/Reportes"})
public class Servelet_Reportes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");

        if (request.getSession().getAttribute("admin") != null) {

            request.getSession().setAttribute("activar", 19);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Reportes/listado_Reportes.jsp");
            rd.forward(request, response);

        } else {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "GestionARG";
    }

}
