package Gestor;

import Modelo.DTO.DTO_Encargo;
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
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Encargos (fecha, vigencia, id_estado) VALUES (?,1,1)");
            ps.setString(1, e.getFecha());
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
            ResultSet rs = st.executeQuery("SELECT id_encargo, fecha, id_estado FROM Encargos WHERE vigencia = 1 ORDER BY fecha DESC");
            while (rs.next()) {
                Encargo e = new Encargo();
                e.setId_encargo(rs.getInt(1));
                e.setFecha(rs.getString(2));
                e.setId_estado(rs.getInt(3));
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
    
    public ArrayList<DTO_Encargo> obtenerEncargosDTO() {
        ArrayList<DTO_Encargo> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT e.id_encargo, e.fecha, es.nombre, SUM(d.importe * d.cantidad) FROM Encargos e JOIN Estados es ON e.id_estado = es.id_estado JOIN Detalle_Encargos d ON d.id_encargo = e.id_encargo WHERE e.vigencia = 1 GROUP BY e.id_encargo, e.fecha, es.nombre ORDER BY e.fecha DESC ");
            while (rs.next()) {
                DTO_Encargo e = new DTO_Encargo();
                e.setId_encargo(rs.getInt(1));
                e.setFecha(rs.getString(2));
                e.setEstado(rs.getString(3));
                e.setCantidad(rs.getInt(4));
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
            PreparedStatement ps = conexion.prepareStatement("SELECT id_encargo, fecha, id_estado FROM Encargos WHERE id_encargo = ?");
            ps.setInt(1, id_encargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                e = new Encargo();
                e.setId_encargo(rs.getInt(1));
                e.setFecha(rs.getString(2));
                e.setId_estado(rs.getInt(3));
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
            PreparedStatement ps = conexion.prepareStatement("UPDATE Encargos SET fecha = ?, id_estado = ? WHERE id_encargo = ?");
            ps.setString(1, e.getFecha());
            ps.setInt(2, e.getId_estado());
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
            PreparedStatement ps = conexion.prepareStatement("UPDATE Encargos SET vigencia = 0 WHERE id_encargo = ?");
            ps.setInt(1, id_encargo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public int obtenerUltimoIdEncargo() {
        int id = 0;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT TOP 1 id_encargo FROM Encargos ORDER BY 1 DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return id;
    }

}
