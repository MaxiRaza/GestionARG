package Gestor;

import Modelo.DTO.DTO_Deposito;
import Modelo.Deposito;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Depositos {

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

    public void agregarDeposito(Deposito d) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Depositos (ubicacion, vigencia, id_sucursal) VALUES (?,1, ?)");
            ps.setString(1, d.getUbicacion());
            ps.setInt(2, d.getId_sucursal());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Deposito> obtenerDepositos() {
        ArrayList<Deposito> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_deposito, ubicacion, id_sucursal FROM Depositos WHERE vigencia = 1");
            while (rs.next()) {
                Deposito d = new Deposito();
                d.setId_deposito(rs.getInt(1));
                d.setUbicacion(rs.getString(2));
                d.setId_sucursal(rs.getInt(3));
                lista.add(d);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Deposito obtenerDeposito(int id_deposito) {
        Deposito d = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_deposito, ubicacion, id_sucursal FROM Depositos WHERE id_deposito = ?");
            ps.setInt(1, id_deposito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                d = new Deposito();
                d.setId_deposito(rs.getInt(1));
                d.setUbicacion(rs.getString(2));
                d.setId_sucursal(rs.getInt(3));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return d;
    }

    public void actualizarDeposito(Deposito d) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Depositos SET ubicacion = ?, id_sucursal = ? WHERE id_deposito = ?");
            ps.setString(1, d.getUbicacion());
            ps.setInt(2, d.getId_sucursal());
            ps.setInt(3, d.getId_deposito());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarDeposito(int id_deposito) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Depositos SET vigencia = 0 WHERE id_deposito = ?");
            ps.setInt(1, id_deposito);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<DTO_Deposito> obtenerDepositosDTO() {
        ArrayList<DTO_Deposito> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT d.id_deposito, d.ubicacion, s.nombre FROM Depositos d JOIN Sucursales s ON d.id_sucursal = s.id_sucursal WHERE d.vigencia = 1");
            while (rs.next()) {
                DTO_Deposito d = new DTO_Deposito();
                d.setId_deposito(rs.getInt(1));
                d.setDeposito(rs.getString(2));
                d.setSucursal(rs.getString(3));
                lista.add(d);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Depositos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
}
