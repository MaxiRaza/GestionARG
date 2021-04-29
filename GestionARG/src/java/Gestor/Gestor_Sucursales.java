package Gestor;

import Modelo.Sucursal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Sucursales {

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

    public void agregarSucursal(Sucursal s) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Sucursales (nombre, direccion) VALUES (?,?)");
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDireccion());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Sucursal> obtenerSucursales() {
        ArrayList<Sucursal> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_sucursal, nombre, direccion FROM Sucursales");
            while (rs.next()) {
                Sucursal s = new Sucursal();
                s.setId_sucursal(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setDireccion(rs.getString(3));
                lista.add(s);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Sucursal obtenerSucursal(int id_sucursal) {
        Sucursal s = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_sucursal, nombre, direccion FROM Sucursales WHERE id_sucursal = ?");
            ps.setInt(1, id_sucursal);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Sucursal();
                s.setId_sucursal(rs.getInt(1));
                s.setNombre(rs.getString(2));
                s.setDireccion(rs.getString(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return s;
    }

    public void actualizarSucursal(Sucursal s) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Sucursales SET nombre = ?, direccion = ? WHERE id_sucursal = ?");
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDireccion());
            ps.setInt(3, s.getId_sucursal());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarSucursal(int id_sucursal) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Sucursales WHERE id_sucursal = ?");
            ps.setInt(1, id_sucursal);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Sucursales.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
