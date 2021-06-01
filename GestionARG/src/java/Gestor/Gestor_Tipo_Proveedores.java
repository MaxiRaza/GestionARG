package Gestor;

import Modelo.Tipo_Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Tipo_Proveedores {

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

    public void agregarTipoProveedor(Tipo_Proveedor tp) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Tipo_Proveedores (nombre, vigencia) VALUES (?,1)");
            ps.setString(1, tp.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Tipo_Proveedor> obtenerTipoProveedores() {
        ArrayList<Tipo_Proveedor> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_tipo_proveedor, nombre FROM Tipo_Proveedores WHERE vigencia = 1");
            while (rs.next()) {
                Tipo_Proveedor tp = new Tipo_Proveedor();
                tp.setId_tipo_proveedor(rs.getInt(1));
                tp.setNombre(rs.getString(2));
                lista.add(tp);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Tipo_Proveedor obtenerTipoProveedor(int id_tipo_proveedor) {
        Tipo_Proveedor tp = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_tipo_proveedor, nombre FROM Tipo_Proveedores WHERE id_tipo_proveedor = ?");
            ps.setInt(1, id_tipo_proveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tp = new Tipo_Proveedor();
                tp.setId_tipo_proveedor(rs.getInt(1));
                tp.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return tp;
    }

    public void actualizarTipoProveedor(Tipo_Proveedor tp) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Tipo_Proveedores SET nombre = ? WHERE id_tipo_proveedor = ?");
            ps.setString(1, tp.getNombre());
            ps.setInt(2, tp.getId_tipo_proveedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarTipoProveedor(int id_tipo_proveedor) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Tipo_Proveedores SET vigencia = 0 WHERE id_tipo_proveedor = ?");
            ps.setInt(1, id_tipo_proveedor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
