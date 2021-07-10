package Gestor;

import Modelo.DTO.DTO_Producto;
import Modelo.Factura;
import Modelo.Marca;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Reportes {

    private Connection conexion;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=GestionARG";
    private String USER = "sa";
    private String PASS = "1234";

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public ArrayList<DTO_Producto> obtenerTop10ProductosVentas() {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 10 p.id_producto, p.nombre, m.nombre, c.nombre, COUNT(p.precio*f.cantidad) FROM Productos p JOIN Detalle_Facturas f ON p.id_producto = f.id_producto JOIN Marcas m on p.id_marca = m.id_marca JOIN Categorias c ON m.id_categoria = c.id_categoria GROUP BY p.id_producto, p.nombre, m.nombre, c.nombre");
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setMarca(rs.getString(3));
                p.setCategoria(rs.getString(4));
                p.setStock(rs.getInt(5));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public ArrayList<Marca> obtenerTop5MarcasMasVendidas() {
        ArrayList<Marca> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT TOP 5  m.nombre,  COUNT(f.id_producto * f.cantidad)FROM Productos p JOIN Detalle_Facturas f ON p.id_producto = f.id_producto JOIN Marcas m ON p.id_marca = m.id_marca GROUP BY m.nombre ORDER BY COUNT(f.id_producto * f.cantidad) DESC");
            while (rs.next()) {
                Marca m = new Marca();
                m.setNombre(rs.getString(1));
                m.setId_categoria(rs.getInt(2));
                lista.add(m);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<Factura> obtenerFacturacionMensual() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT SUM(f.cantidad * f.importe), MONTH (fa.fecha) FROM Detalle_Facturas f JOIN Facturas fa ON fa.id_factura = fa.id_factura WHERE YEAR(fa.fecha) = 2021 GROUP BY MONTH (fa.fecha) ORDER BY SUM(f.cantidad * f.importe) DESC");
            while (rs.next()) {
                Factura f = new Factura();
                f.setDescuento(rs.getFloat(1));
                f.setId_factura(rs.getInt(2));
                lista.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<Factura> obtenerFacturacionMensualLineal() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT SUM(f.cantidad * f.importe), MONTH (fa.fecha) FROM Detalle_Facturas f JOIN Facturas fa ON fa.id_factura = fa.id_factura WHERE YEAR(fa.fecha) = 2021 GROUP BY MONTH (fa.fecha) ORDER BY MONTH (fa.fecha) ");
            while (rs.next()) {
                Factura f = new Factura();
                f.setDescuento(rs.getFloat(1));
                f.setId_factura(rs.getInt(2));
                lista.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

}
