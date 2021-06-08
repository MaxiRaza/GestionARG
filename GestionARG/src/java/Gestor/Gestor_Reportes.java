package Gestor;

import Modelo.DTO.DTO_Producto;
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

    public ArrayList<DTO_Producto> obtenerTopProductos() {
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

//    public Marca obtenerMarca(int id_marca) {
//        Marca m = null;
//        try {
//            abrirConexion();
//            PreparedStatement ps = conexion.prepareStatement("SELECT id_marca, nombre, id_categoria FROM Marcas WHERE id_marca = ?");
//            ps.setInt(1, id_marca);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                m = new Marca();
//                m.setId_marca(rs.getInt(1));
//                m.setNombre(rs.getString(2));
//                m.setId_categoria(rs.getInt(3));
//            }
//            ps.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Gestor_Reportes.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            cerrarConexion();
//        }
//        return m;
//    }
}
