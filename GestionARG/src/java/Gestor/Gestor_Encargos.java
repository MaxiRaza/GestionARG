package Gestor;

import Modelo.Encargo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Encargos {

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

    public void agregarEncargo(Encargo e) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Encargos (fecha, id_proveedor) VALUES (?,?)");
            ps.setString(1, e.getFecha());
            ps.setInt(2, e.getId_proveedor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Encargo> obtenerEncargos() {
        ArrayList<Encargo> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_encargo, fecha, id_proveedor FROM Encargos");
            while (rs.next()) {
                Encargo e = new Encargo();
                e.setId_encargo(rs.getInt(1));
                e.setFecha(rs.getString(2));
                e.setId_proveedor(rs.getInt(3));
                lista.add(e);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Encargo obtenerEncargo(int id_encargo) {
        Encargo e = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_encargo, fecha, id_proveedor FROM Encargos WHERE id_encargo = ?");
            ps.setInt(1, id_encargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Encargo();
                e.setId_encargo(rs.getInt(1));
                e.setFecha(rs.getString(2));
                e.setId_proveedor(rs.getInt(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return e;
    }

    public void actualizarEncargo(Encargo e) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Encargos SET fecha = ?, id_proveedor = ?  WHERE id_encargo = ?");
            ps.setString(1, e.getFecha());
            ps.setInt(2, e.getId_proveedor());
            ps.setInt(3, e.getId_encargo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarEncargo(int id_encargo) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Encargos WHERE id_encargo = ?");
            ps.setInt(1, id_encargo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
