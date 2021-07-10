package Servelet;

import Gestor.Gestor_Reportes;
import Modelo.DTO.DTO_Producto;
import Modelo.Factura;
import Modelo.Marca;
import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

@WebServlet(name = "Reportess", urlPatterns = {"/Reportess"})
public class Servelet_Reportes extends HttpServlet {

    Gestor_Reportes gr = new Gestor_Reportes();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modo = request.getParameter("modo");
        request.getSession().setAttribute("t", false);
        request.getSession().setAttribute("servelet", "Reportess");
        request.getSession().setAttribute("co", false);
        response.setContentType("image/PNG");

        if (modo == null) {

            if (request.getSession().getAttribute("log") != null) {

                request.getSession().setAttribute("activar", 19);
                request.setAttribute("listadoTopProductos", gr.obtenerTop10ProductosVentas());
                request.setAttribute("listadoTopMarcas", gr.obtenerTop5MarcasMasVendidas());
                request.setAttribute("listadoGananciaMensual", gr.obtenerFacturacionMensual());

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Reportes/listado_Reportes.jsp");
                rd.forward(request, response);

            } else {

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Login/login.jsp");
                rd.forward(request, response);

            }

        } else if (modo.equals("tema")) {

            if (request.getParameter("color").equals("oscuro")) {

                request.getSession().setAttribute("color", "claro");

            } else {

                request.getSession().setAttribute("color", "oscuro");

            }

        } else if (modo.equals("torta")) {

            DefaultPieDataset torta = new DefaultPieDataset();

            for (Marca m : gr.obtenerTop5MarcasMasVendidas()) {

                torta.setValue(m.getNombre(), m.getId_categoria());

            }

            JFreeChart chart = ChartFactory.createPieChart3D("Marcas mas Vendidas", torta, true, true, true);

            PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle(270);
            plot.setForegroundAlpha(0.60f);
            plot.setInteriorGap(0.08);
            plot.setDarkerSides(true);

            OutputStream out = response.getOutputStream();
            ChartUtilities.writeChartAsPNG(out, chart, 1000, 1000);

        } else if (modo.equals("barra")) {

            DefaultCategoryDataset barra = new DefaultCategoryDataset();

            for (DTO_Producto p : gr.obtenerTop10ProductosVentas()) {

                barra.setValue(p.getStock(), p.getNombre(), "");

            }

            JFreeChart chart = ChartFactory.createBarChart3D("Productos mas vendidos", "", "Ventas",
                    barra, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.LIGHT_GRAY);
            chart.getTitle().setPaint(Color.black);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.red);

            OutputStream out = response.getOutputStream();
            ChartUtilities.writeChartAsPNG(out, chart, 1000, 1000);

        } else if (modo.equals("lineal")) {

            DefaultCategoryDataset lineal = new DefaultCategoryDataset();

            for (Factura f : gr.obtenerFacturacionMensualLineal()) {
                switch (f.getId_factura()) {
                    case 1:
                        lineal.addValue(f.getDescuento(), "Importe", "Enero");
                        break;
                    case 2:
                        lineal.addValue(f.getDescuento(), "Importe", "Febrero");
                        break;
                    case 3:
                        lineal.addValue(f.getDescuento(), "Importe", "Marzo");
                        break;
                    case 4:
                        lineal.addValue(f.getDescuento(), "Importe", "Abril");
                        break;
                    case 5:
                        lineal.addValue(f.getDescuento(), "Importe", "Mayo");
                        break;
                    case 6:
                        lineal.addValue(f.getDescuento(), "Importe", "Junio");
                        break;
                    case 7:
                        lineal.addValue(f.getDescuento(), "Importe", "Julio");
                        break;
                    case 8:
                        lineal.addValue(f.getDescuento(), "Importe", "Agosto");
                        break;
                    case 9:
                        lineal.addValue(f.getDescuento(), "Importe", "Septiembre");
                        break;
                    case 10:
                        lineal.addValue(f.getDescuento(), "Importe", "Octubre");
                        break;
                    case 11:
                        lineal.addValue(f.getDescuento(), "Importe", "Noviembre");
                        break;
                    case 12:
                        lineal.addValue(f.getDescuento(), "Importe", "Diciembre");
                        break;
                }

            }

            JFreeChart chart = ChartFactory.createLineChart("Facturación Mensual (2021)",
                    "Mes", "Facturación", lineal, PlotOrientation.VERTICAL,
                    true, true, true);

            OutputStream out = response.getOutputStream();
            ChartUtilities.writeChartAsPNG(out, chart, 1500, 1000);

        }

        request.setAttribute("listadoTopProductos", gr.obtenerTop10ProductosVentas());
        request.setAttribute("listadoTopMarcas", gr.obtenerTop5MarcasMasVendidas());
        request.setAttribute("listadoGananciaMensual", gr.obtenerFacturacionMensual());
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Reportes/listado_Reportes.jsp");
        rd.forward(request, response);

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
