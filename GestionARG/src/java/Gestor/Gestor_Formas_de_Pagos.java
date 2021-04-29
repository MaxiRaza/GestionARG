package Gestor;

import Modelo.Forma_de_Pago;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Formas_de_Pagos {

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

    public void agregarFormaDePago(Forma_de_Pago fdp) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Formas_de_Pagos (nombre) VALUES (?)");
            ps.setString(1, fdp.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Formas_de_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Forma_de_Pago> obtenerFormasDePagos() {
        ArrayList<Forma_de_Pago> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_forma_de_pago, nombre FROM Formas_de_Pagos");
            while (rs.next()) {
                Forma_de_Pago fdp = new Forma_de_Pago();
                fdp.setId_forma_de_pago(rs.getInt(1));
                fdp.setNombre(rs.getString(2));
                lista.add(fdp);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Formas_de_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Forma_de_Pago obtenerFormaDePago(int id_forma_de_pago) {
        Forma_de_Pago fdp = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_forma_de_pago, nombre FROM Formas_de_Pagos WHERE id_forma_de_pago = ?");
            ps.setInt(1, id_forma_de_pago);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                fdp = new Forma_de_Pago();
                fdp.setId_forma_de_pago(rs.getInt(1));
                fdp.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Formas_de_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return fdp;
    }

    public void actualizarFormaDePago(Forma_de_Pago fdp) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Formas_de_Pagos SET nombre = ? WHERE id_Forma_de_Pago = ?");
            ps.setString(1, fdp.getNombre());
            ps.setInt(2, fdp.getId_forma_de_pago());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Formas_de_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarFormaDePago(int id_forma_de_pago) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Formas_de_Pagos WHERE id_forma_de_pago = ?");
            ps.setInt(1, id_forma_de_pago);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Formas_de_Pagos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
