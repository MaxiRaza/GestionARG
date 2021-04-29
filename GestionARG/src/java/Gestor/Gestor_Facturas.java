package Gestor;

import Modelo.Factura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Facturas {

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

    public void agregarFactura(Factura f) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Facturas (fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago) VALUES (?,?,?,?,?,?)");
            ps.setString(1, f.getFecha());
            ps.setFloat(2, f.getDescuento());
            ps.setInt(3, f.getId_sucursal());
            ps.setInt(4, f.getId_cliente());
            ps.setInt(5, f.getId_usuario());
            ps.setInt(6, f.getId_forma_de_pago());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Factura> obtenerFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_factura, fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago FROM Facturas");
            while (rs.next()) {
                Factura f = new Factura();
                f.setId_factura(rs.getInt(1));
                f.setFecha(rs.getString(2));
                f.setDescuento(rs.getFloat(3));
                f.setId_sucursal(rs.getInt(4));
                f.setId_cliente(rs.getInt(5));
                f.setId_usuario(rs.getInt(6));
                f.setId_forma_de_pago(rs.getInt(7));
                lista.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Factura obtenerFactura(int id_factura) {
        Factura f = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_factura, fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago FROM Facturas WHERE id_factura = ?");
            ps.setInt(1, id_factura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f = new Factura();
                f.setId_factura(rs.getInt(1));
                f.setFecha(rs.getString(2));
                f.setDescuento(rs.getFloat(3));
                f.setId_sucursal(rs.getInt(4));
                f.setId_cliente(rs.getInt(5));
                f.setId_usuario(rs.getInt(6));
                f.setId_forma_de_pago(rs.getInt(7));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return f;
    }

    public void actualizarFactura(Factura f) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Facturas SET fecha = ?, descuento = ?, id_sucursal = ?, id_cliente = ?, id_usuario = ?, id_forma_de_pago = ?  WHERE id_factura = ?");
            ps.setString(1, f.getFecha());
            ps.setFloat(2, f.getDescuento());
            ps.setInt(3, f.getId_sucursal());
            ps.setInt(4, f.getId_cliente());
            ps.setInt(5, f.getId_usuario());
            ps.setInt(6, f.getId_forma_de_pago());
            ps.setInt(6, f.getId_factura());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarFactura(int id_factura) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Facturas WHERE id_factura = ?");
            ps.setInt(1, id_factura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
