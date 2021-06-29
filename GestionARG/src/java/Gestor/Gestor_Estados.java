package Gestor;

import Modelo.Estado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Estados {

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

    public void agregarEstado(Estado e) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Estados (nombre, vigencia) VALUES (?,1)");
            ps.setString(1, e.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Estados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Estado> obtenerEstados() {
        ArrayList<Estado> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_estado, nombre FROM Estados WHERE vigencia = 1");
            while (rs.next()) {
                Estado e = new Estado();
                e.setId_estado(rs.getInt(1));
                e.setNombre(rs.getString(2));
                lista.add(e);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Estados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Estado obtenerEstado(int id_estado) {
        Estado e = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_estado, nombre FROM Estados WHERE id_estado = ?");
            ps.setInt(1, id_estado);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Estado();
                e.setId_estado(rs.getInt(1));
                e.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Estados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return e;
    }

    public void actualizarEstado(Estado e) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Estados SET nombre = ? WHERE id_estado = ?");
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getId_estado());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Estados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarEstado(int id_estado) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Estados SET vigencia = 0 WHERE id_estado = ?");
            ps.setInt(1, id_estado);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Estados.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
