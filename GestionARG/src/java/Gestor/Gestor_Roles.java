package Gestor;

import Modelo.Rol;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Roles {

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

    public void agregarRol(Rol r) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Roles (nombre) VALUES (?)");
            ps.setString(1, r.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Roles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Rol> obtenerRoles() {
        ArrayList<Rol> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_rol, nombre FROM Roles");
            while (rs.next()) {
                Rol r = new Rol();
                r.setId_rol(rs.getInt(1));
                r.setNombre(rs.getString(2));
                lista.add(r);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Roles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Rol obtenerRol(int id_rol) {
        Rol r = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_rol, nombre FROM Roles WHERE id_rol = ?");
            ps.setInt(1, id_rol);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                r = new Rol();
                r.setId_rol(rs.getInt(1));
                r.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Roles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return r;
    }

    public void actualizarRol(Rol r) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Roles SET nombre = ? WHERE id_rol = ?");
            ps.setString(1, r.getNombre());
            ps.setInt(2, r.getId_rol());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Roles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarRol(int id_rol) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Roles WHERE id_rol = ?");
            ps.setInt(1, id_rol);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Roles.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
