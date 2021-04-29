package Gestor;

import Modelo.Clasificacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Clasificaciones {

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

    public void agregarClasificacion(Clasificacion c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Clasificaciones (nombre) VALUES (?)");
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clasificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Clasificacion> obtenerClasificaciones() {
        ArrayList<Clasificacion> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_clasificacion, nombre FROM Clasificaciones");
            while (rs.next()) {
                Clasificacion c = new Clasificacion();
                c.setId_clasificacion(rs.getInt(1));
                c.setNombre(rs.getString(2));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clasificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Clasificacion obtenerClasificacion(int id_clasificacion) {
        Clasificacion c = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_clasificacion, nombre FROM Clasificaciones WHERE id_clasificacion = ?");
            ps.setInt(1, id_clasificacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Clasificacion();
                c.setId_clasificacion(rs.getInt(1));
                c.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clasificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return c;
    }

    public void actualizarClasificacion(Clasificacion c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Clasificaciones SET nombre = ? WHERE id_clasificacion = ?");
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getId_clasificacion());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clasificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarClasificacion(int id_clasificacion) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Clasificaciones WHERE id_clasificacion = ?");
            ps.setInt(1, id_clasificacion);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Clasificaciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
