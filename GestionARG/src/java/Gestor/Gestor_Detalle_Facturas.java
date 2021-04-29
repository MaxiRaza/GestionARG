package Gestor;

import Modelo.Detalle_Factura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Detalle_Facturas {

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

    public void agregarDetalleFactura(Detalle_Factura df) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Detalle_Facturas (cantidad, importe, id_factura, id_producto) VALUES (?,?,?,?)");
            ps.setFloat(1, df.getCantidad());
            ps.setFloat(2, df.getImporte());
            ps.setInt(3, df.getId_factura());
            ps.setInt(4, df.getId_producto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Detalle_Factura> obtenerDetalleFacturas() {
        ArrayList<Detalle_Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_detalle_factura, fecha, cantidad, importe, id_factura, id_producto FROM Detalle_Facturas");
            while (rs.next()) {
                Detalle_Factura df = new Detalle_Factura();
                df.setId_deatalle_factura(rs.getInt(1));
                df.setCantidad(rs.getFloat(2));
                df.setImporte(rs.getFloat(3));
                df.setId_factura(rs.getInt(4));
                df.setId_producto(rs.getInt(5));
                lista.add(df);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Detalle_Factura obtenerDetalleFactura(int id_detalle_factura) {
        Detalle_Factura df = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_detalle_factura, fecha, cantidad, importe, id_factura, id_producto FROM Detalle_Facturas WHERE id_detalle_factura = ?");
            ps.setInt(1, id_detalle_factura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                df = new Detalle_Factura();
                df.setId_deatalle_factura(rs.getInt(1));
                df.setCantidad(rs.getFloat(2));
                df.setImporte(rs.getFloat(3));
                df.setId_factura(rs.getInt(4));
                df.setId_producto(rs.getInt(5));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return df;
    }

    public void actualizarDetalleFactura(Detalle_Factura df) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Detalle_Facturas SET fecha = ?, cantidad = ?, importe = ?, id_factura = ?, id_producto = ? WHERE id_detalle_factura = ?");
            ps.setFloat(1, df.getCantidad());
            ps.setFloat(2, df.getImporte());
            ps.setInt(3, df.getId_factura());
            ps.setInt(4, df.getId_producto());
            ps.setInt(5, df.getId_deatalle_factura());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarDetalleFactura(int id_detalle_factura) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Detalle_Facturas WHERE id_detalle_factura = ?");
            ps.setInt(1, id_detalle_factura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
