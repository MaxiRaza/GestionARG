package Gestor;

import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Proveedores {

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

    public void agregarProveedor(Proveedor p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Proveedores (nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion) VALUES (?,?,?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getCuit());
            ps.setString(3, p.getDireccion());
            ps.setInt(4, p.getId_contacto());
            ps.setInt(5, p.getId_tipo_proveedor());
            ps.setInt(6, p.getId_clasificacion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_proveedor, nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion FROM Proveedores");
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getDouble(3));
                p.setDireccion(rs.getString(4));
                p.setId_contacto(rs.getInt(5));
                p.setId_tipo_proveedor(rs.getInt(6));
                p.setId_clasificacion(rs.getInt(7));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Proveedor obtenerProveedor(int id_proveedor) {
        Proveedor p = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_proveedor, nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion FROM Proveedores WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getDouble(3));
                p.setDireccion(rs.getString(4));
                p.setId_contacto(rs.getInt(5));
                p.setId_tipo_proveedor(rs.getInt(6));
                p.setId_clasificacion(rs.getInt(7));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return p;
    }

    public void actualizarProveedor(Proveedor p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Proveedores SET nombre = ?, cuit = ?, direccion = ?, id_contacto = ?, id_tipo_proveedor = ?, id_clasificacion = ? WHERE id_proveedor = ?");
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getCuit());
            ps.setString(3, p.getDireccion());
            ps.setInt(4, p.getId_contacto());
            ps.setInt(5, p.getId_tipo_proveedor());
            ps.setInt(6, p.getId_clasificacion());
            ps.setInt(7, p.getId_proveedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarProveedor(int id_proveedor) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Proveedores WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
