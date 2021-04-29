package Gestor;

import Modelo.Detalle_Encargo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Detalle_Encargos {

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

    public void agregarDetalleEncargo(Detalle_Encargo de) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Detalle_Encargos (cantidad, importe, id_producto, id_encargo) VALUES (?,?,?,?)");
            ps.setFloat(1, de.getCantidad());
            ps.setFloat(2, de.getImporte());
            ps.setInt(3, de.getId_producto());
            ps.setInt(4, de.getId_encargo());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Detalle_Encargo> obtenerDetalleEncargos() {
        ArrayList<Detalle_Encargo> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_detalle_encargo, cantidad, importe, id_producto, id_encargo FROM Detalle_Encargos");
            while (rs.next()) {
                Detalle_Encargo de = new Detalle_Encargo();
                de.setId_detalle_encargo(rs.getInt(1));
                de.setCantidad(rs.getFloat(2));
                de.setImporte(rs.getFloat(3));
                de.setId_producto(rs.getInt(4));
                de.setId_encargo(rs.getInt(5));
                lista.add(de);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Detalle_Encargo obtenerDetalleEncargo(int id_detalle_encargo) {
        Detalle_Encargo de = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_detalle_encargo, cantidad, importe, id_producto, id_encargo FROM Detalle_Encargos WHERE id_detalle_encargo = ?");
            ps.setInt(1, id_detalle_encargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                de = new Detalle_Encargo();
                de.setId_detalle_encargo(rs.getInt(1));
                de.setCantidad(rs.getFloat(2));
                de.setImporte(rs.getFloat(3));
                de.setId_producto(rs.getInt(4));
                de.setId_encargo(rs.getInt(5));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return de;
    }

    public void actualizarDetalleEncargo(Detalle_Encargo de) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Detalle_Encargos SET cantidad = ?, importe = ?, id_producto = ?, id_encargo = ?  WHERE id_detalle_encargo = ?");
            ps.setFloat(1, de.getCantidad());
            ps.setFloat(2, de.getImporte());
            ps.setInt(3, de.getId_producto());
            ps.setInt(4, de.getId_encargo());
            ps.setInt(5, de.getId_detalle_encargo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarDetalleEncargo(int id_detalle_encargo) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Detalle_Encargos WHERE id_detalle_encargo = ?");
            ps.setInt(1, id_detalle_encargo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
