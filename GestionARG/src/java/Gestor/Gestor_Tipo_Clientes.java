package Gestor;

import Modelo.Tipo_Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Tipo_Clientes {

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

    public void agregarTipoCliente(Tipo_Cliente tc) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Tipo_Clientes (nombre, vigencia) VALUES (?,1)");
            ps.setString(1, tc.getNombre());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Tipo_Cliente> obtenerTipoClientes() {
        ArrayList<Tipo_Cliente> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_tipo_cliente, nombre FROM Tipo_Clientes WHERE vigencia = 1");
            while (rs.next()) {
                Tipo_Cliente tc = new Tipo_Cliente();
                tc.setId_tipo_cliente(rs.getInt(1));
                tc.setNombre(rs.getString(2));
                lista.add(tc);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Tipo_Cliente obtenerTipoCliente(int id_tipo_cliente) {
        Tipo_Cliente tc = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_tipo_cliente, nombre FROM Tipo_Clientes WHERE id_tipo_cliente = ?");
            ps.setInt(1, id_tipo_cliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tc = new Tipo_Cliente();
                tc.setId_tipo_cliente(rs.getInt(1));
                tc.setNombre(rs.getString(2));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return tc;
    }

    public void actualizarTipoCliente(Tipo_Cliente tc) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Tipo_Clientes SET nombre = ? WHERE id_tipo_cliente = ?");
            ps.setString(1, tc.getNombre());
            ps.setInt(2, tc.getId_tipo_cliente());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarTipoCliente(int id_tipo_cliente) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Tipo_Clientes SET vigencia = 0 WHERE id_tipo_cliente = ?");
            ps.setInt(1, id_tipo_cliente);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Tipo_Clientes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
}
